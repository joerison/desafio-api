package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.repository.IntegranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IntegranteServiceTest {

    @Mock
    private IntegranteRepository integranteRepository;

    @InjectMocks
    private IntegranteService integranteService;

    private IntegranteDTO integranteDTO;
    private Integrante integranteEntity;

    @BeforeEach
    public void setUp() {
        integranteDTO = new IntegranteDTO();
        integranteDTO.setNome("JogadorX");

        integranteEntity = new Integrante();
        integranteEntity.setId(10L);
        integranteEntity.setNome("JogadorX");
    }

    @Test
    public void salvarDevolveDTOComIdEChamaRepository() {
        when(integranteRepository.save(any(Integrante.class))).thenReturn(integranteEntity);

        IntegranteDTO resultado = integranteService.salvar(integranteDTO);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        verify(integranteRepository, times(1)).save(any(Integrante.class));
    }

    @Test
    public void listarDevolveListaOrdenadaDecrescente() {
        Integrante i1 = new Integrante(); i1.setId(1L);
        Integrante i2 = new Integrante(); i2.setId(2L);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        when(integranteRepository.findAll(sort)).thenReturn(Arrays.asList(i2, i1));

        List<Integrante> resultado = integranteService.listar();

        assertEquals(2, resultado.size());
        assertEquals(2L, resultado.get(0).getId());
        assertEquals(1L, resultado.get(1).getId());
        verify(integranteRepository, times(1)).findAll(sort);
    }

    @Test
    public void listarRetornaListaVaziaQuandoNenhumIntegrante() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        when(integranteRepository.findAll(sort)).thenReturn(Collections.emptyList());

        List<Integrante> resultado = integranteService.listar();

        assertTrue(resultado.isEmpty());
        verify(integranteRepository, times(1)).findAll(sort);
    }
}
