package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.dto.TimeDTO;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.repository.TimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {

    @Mock
    private TimeRepository timeRepository;

    @InjectMocks
    private TimeService timeService;

    private TimeDTO timeDto;
    private Time timeEntity;

    @BeforeEach
    public void setUp() {
        timeDto = new TimeDTO();
        LocalDate data = LocalDate.of(2021, 1, 1);
        timeDto.setData(data);
        // caso TimeDTO possua outros campos, podem ser inicializados aqui

        timeEntity = new Time();
        timeEntity.setId(100L);
        timeEntity.setData(data);
    }

    @Test
    public void deveSalvarTimeQuandoDataNaoExistir() {
        // dado que não existe time para a data informada
        when(timeRepository.findByData(timeDto.getData())).thenReturn(null);
        when(timeRepository.save(any(Time.class))).thenReturn(timeEntity);

        TimeDTO resultado = timeService.salvar(timeDto);

        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals(timeDto.getData(), resultado.getData());
        verify(timeRepository, times(1)).findByData(timeDto.getData());
        verify(timeRepository, times(1)).save(any(Time.class));
    }

    @Test
    public void deveLancarExcecaoQuandoDataJaExistir() {
        // dado que já existe time para a data informada
        when(timeRepository.findByData(timeDto.getData())).thenReturn(timeEntity);

        RuntimeException erro = assertThrows(RuntimeException.class, () ->
                timeService.salvar(timeDto)
        );
        assertTrue(erro.getMessage().contains("Já consta um cadastro de Time para essa data!"));
        verify(timeRepository, times(1)).findByData(timeDto.getData());
        verify(timeRepository, never()).save(any(Time.class));
    }

    @Test
    public void deveListarTimesOrdenadosPorIdDescendente() {
        Time t1 = new Time(); t1.setId(1L);
        Time t2 = new Time(); t2.setId(2L);
        List<Time> listaEsperada = Arrays.asList(t2, t1);

        Sort ordenacao = Sort.by(Sort.Direction.DESC, "id");
        when(timeRepository.findAll(ordenacao)).thenReturn(listaEsperada);

        List<Time> resultado = timeService.listar();

        assertEquals(2, resultado.size());
        assertEquals(2L, resultado.get(0).getId());
        assertEquals(1L, resultado.get(1).getId());
        verify(timeRepository, times(1)).findAll(ordenacao);
    }

    @Test
    public void deveRetornarListaVaziaQuandoNaoHouverTimes() {
        Sort ordenacao = Sort.by(Sort.Direction.DESC, "id");
        when(timeRepository.findAll(ordenacao)).thenReturn(Collections.emptyList());

        List<Time> resultado = timeService.listar();

        assertTrue(resultado.isEmpty());
        verify(timeRepository, times(1)).findAll(ordenacao);
    }
}