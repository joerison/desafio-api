package com.joerison.duxusdesafio.service;


import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {

    private final TimeRepository timeRepository;

    public ApiService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        Time time = timeRepository.findByData(data);
        if (time == null){
            throw new RuntimeException("Não consta Time para essa data informada");
        }
        return time;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

}
