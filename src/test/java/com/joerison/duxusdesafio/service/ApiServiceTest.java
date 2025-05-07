package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.model.*;
import com.joerison.duxusdesafio.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    @Mock
    private TimeRepository timeRepository;
    @Mock
    private IntegranteRepository integranteRepository;
    @Mock
    private FranquiaRepository franquiaRepository;
    @Mock
    private FuncaoRepository funcaoRepository;

    @InjectMocks
    private ApiService apiService;

    private Franquia franquiaUm;
    private Franquia franquiaDois;
    private Funcao funcaoUm;
    private Funcao funcaoDois;
    private Integrante integranteUm;
    private Integrante integranteDois;
    private Time timeUm;
    private Time timeDois;
    private Time timeTres;

    @BeforeEach
    public void setUp() {
        franquiaUm = new Franquia();
        franquiaUm.setId(1L);
        franquiaUm.setDescricao("Futebol");

        franquiaDois = new Franquia();
        franquiaDois.setId(2L);
        franquiaDois.setDescricao("Basquete");

        funcaoUm = new Funcao();
        funcaoUm.setId(1L);
        funcaoUm.setDescricao("Goleiro");
        funcaoUm.setFranquia(franquiaUm);

        funcaoDois = new Funcao();
        funcaoDois.setId(2L);
        funcaoDois.setDescricao("Atacante");
        funcaoDois.setFranquia(franquiaDois);

        integranteUm = new Integrante();
        integranteUm.setId(1L);
        integranteUm.setNome("Jogador1");
        integranteUm.setFuncao(funcaoUm);

        integranteDois = new Integrante();
        integranteDois.setId(2L);
        integranteDois.setNome("Jogador2");
        integranteDois.setFuncao(funcaoDois);

        timeUm = new Time();
        timeUm.setId(1L);
        timeUm.setData(LocalDate.of(2021, 1, 1));
        timeUm.setIntegrantes(Arrays.asList(integranteUm, integranteDois));

        timeDois = new Time();
        timeDois.setId(2L);
        timeDois.setData(LocalDate.of(2021, 1, 8));
        timeDois.setIntegrantes(Arrays.asList(integranteUm, integranteDois));

        timeTres = new Time();
        timeTres.setId(3L);
        timeTres.setData(LocalDate.of(2021, 1, 15));
        timeTres.setIntegrantes(Collections.singletonList(integranteUm));
    }

    @Test
    public void deveRetornarTimeParaDataExistente() {
        LocalDate data = LocalDate.of(2021, 1, 1);
        when(timeRepository.findByData(data)).thenReturn(timeUm);

        Time resultado = apiService.timeDaData(data, Collections.emptyList());

        assertEquals(timeUm, resultado);
    }

    @Test
    public void deveLancarExcecaoSeTimeNaoExistir() {
        LocalDate data = LocalDate.of(2021, 1, 2);
        when(timeRepository.findByData(data)).thenReturn(null);

        RuntimeException erro = assertThrows(RuntimeException.class, () ->
                apiService.timeDaData(data, Collections.emptyList())
        );
        assertTrue(erro.getMessage().contains("Não consta Time"));
    }

    @Test
    public void deveEncontrarIntegranteMaisUsado() {
        when(timeRepository.findAll()).thenReturn(Arrays.asList(timeUm, timeDois, timeTres));
        when(integranteRepository.findById(1L)).thenReturn(Optional.of(integranteUm));

        Integrante resultado = apiService.integranteMaisUsado(null, null);

        assertEquals(integranteUm, resultado);
    }

    @Test
    public void deveRetornarTimeMaisComum() {
        when(timeRepository.findAll()).thenReturn(Arrays.asList(timeUm, timeDois, timeTres));

        Time resultado = apiService.integrantesDoTimeMaisComum(null, null);

        assertEquals(timeUm, resultado);
    }

    @Test
    public void deveRetornarFuncaoMaisComum() {
        when(timeRepository.findAll()).thenReturn(Arrays.asList(timeUm, timeDois, timeTres));
        when(funcaoRepository.findById(1L)).thenReturn(Optional.of(funcaoUm));

        Funcao resultado = apiService.funcaoMaisComum(null, null);

        assertEquals(funcaoUm, resultado);
    }

    @Test
    public void deveRetornarFranquiaMaisFamosa() {
        when(timeRepository.findAll()).thenReturn(Arrays.asList(timeUm, timeDois, timeTres));
        when(franquiaRepository.findById(1L)).thenReturn(Optional.of(franquiaUm));

        Franquia resultado = apiService.franquiaMaisFamosa(null, null);

        assertEquals(franquiaUm, resultado);
    }

    @Test
    public void deveRetornarContagemPorFranquia() {
        when(timeRepository.findAll()).thenReturn(Arrays.asList(timeUm, timeDois, timeTres));

        Map<String, Long> contagem = apiService.contagemPorFranquia(null, null);

        assertEquals(3L, contagem.get("Futebol"));
        assertEquals(2L, contagem.get("Basquete"));
    }

    @Test
    public void deveRetornarContagemPorFuncao() {
        when(timeRepository.findAll()).thenReturn(Arrays.asList(timeUm, timeDois, timeTres));

        Map<String, Long> contagem = apiService.contagemPorFuncao(null, null);

        assertEquals(3L, contagem.get("Goleiro"));
        assertEquals(2L, contagem.get("Atacante"));
    }
}
