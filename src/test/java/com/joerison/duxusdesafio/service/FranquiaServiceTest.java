package com.joerison.duxusdesafio.service;


import com.joerison.duxusdesafio.model.Franquia;
import com.joerison.duxusdesafio.repository.FranquiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FranquiaServiceTest {

    @Mock
    private FranquiaRepository franquiaRepository;

    @InjectMocks
    private FranquiaService franquiaService;

    private Franquia franquiaA;
    private Franquia franquiaB;

    @BeforeEach
    public void setUp() {
        franquiaA = new Franquia();
        franquiaA.setId(1L);
        franquiaA.setDescricao("Futebol");

        franquiaB = new Franquia();
        franquiaB.setId(2L);
        franquiaB.setDescricao("Basquete");
    }

    @Test
    public void listarDevolveTodasAsFranqias() {
        List<Franquia> listaEsperada = Arrays.asList(franquiaA, franquiaB);
        when(franquiaRepository.findAll()).thenReturn(listaEsperada);

        List<Franquia> resultado = franquiaService.listar();

        assertEquals(listaEsperada, resultado);
        verify(franquiaRepository, times(1)).findAll();
    }

    @Test
    public void listarRetornaListaVaziaQuandoNaoExistirNenhuma() {
        when(franquiaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Franquia> resultado = franquiaService.listar();

        assertTrue(resultado.isEmpty());
        verify(franquiaRepository, times(1)).findAll();
    }
}
