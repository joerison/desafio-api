package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.model.Funcao;
import com.joerison.duxusdesafio.repository.FuncaoRepository;
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
public class FuncaoServiceTest {

    @Mock
    private FuncaoRepository funcaoRepository;

    @InjectMocks
    private FuncaoService funcaoService;

    private Funcao funcaoA;
    private Funcao funcaoB;

    @BeforeEach
    public void setUp() {
        funcaoA = new Funcao();
        funcaoA.setId(1L);
        funcaoA.setDescricao("Goleiro");

        funcaoB = new Funcao();
        funcaoB.setId(2L);
        funcaoB.setDescricao("Atacante");
    }

    @Test
    public void listarDevolveFuncoesParaFranquia() {
        Long franquiaId = 1L;
        List<Funcao> listaEsperada = Arrays.asList(funcaoA, funcaoB);
        when(funcaoRepository.findByFranquiaId(franquiaId)).thenReturn(listaEsperada);

        List<Funcao> resultado = funcaoService.listar(franquiaId);

        assertEquals(listaEsperada, resultado);
        verify(funcaoRepository, times(1)).findByFranquiaId(franquiaId);
    }

    @Test
    public void listarRetornaListaVaziaQuandoNaoExistiremFuncoes() {
        Long franquiaId = 2L;
        when(funcaoRepository.findByFranquiaId(franquiaId)).thenReturn(Collections.emptyList());

        List<Funcao> resultado = funcaoService.listar(franquiaId);

        assertTrue(resultado.isEmpty());
        verify(funcaoRepository, times(1)).findByFranquiaId(franquiaId);
    }
}
