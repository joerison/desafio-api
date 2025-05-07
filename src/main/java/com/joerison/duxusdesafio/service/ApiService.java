package com.joerison.duxusdesafio.service;


import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.repository.IntegranteRepository;
import com.joerison.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;

    public ApiService(TimeRepository timeRepository,
                      IntegranteRepository integranteRepository) {
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
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
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal) {
        // 1) busca todos os times do repositório
        List<Time> todosOsTimes = timeRepository.findAll();

        // 2) define limites abertos caso dataInicial/dataFinal sejam null
        LocalDate inicio = (dataInicial != null ? dataInicial : LocalDate.MIN);
        LocalDate fim = (dataFinal   != null ? dataFinal   : LocalDate.MAX);

        // 3) mapa para contar quantas vezes cada integrante apareceu
        Map<Long, Long> contagemPorIntegrante = new HashMap<>();

        // 4) percorre cada time, filtrando pelo intervalo de datas
        for (Time time : todosOsTimes) {
            LocalDate dataDoTime = time.getData();
            if (dataDoTime.isBefore(inicio) || dataDoTime.isAfter(fim)) {
                continue; // ignora times fora do período
            }

            // 5) garante que cada integrante conte apenas uma vez por time
            Set<Long> integrantesContadosNoMesmoTime = new HashSet<>();
            for (Integrante integrante : time.getIntegrantes()) {
                Long idIntegrante = integrante.getId();
                // se já contamos esse integrante neste time, pula
                if (!integrantesContadosNoMesmoTime.add(idIntegrante)) {
                    continue;
                }
                // incrementa a contagem no mapa
                Long quantidadeAtual = contagemPorIntegrante.get(idIntegrante);
                if (quantidadeAtual == null) {
                    contagemPorIntegrante.put(idIntegrante, 1L);
                } else {
                    contagemPorIntegrante.put(idIntegrante, quantidadeAtual + 1);
                }
            }
        }

        // 6) se não houve nenhum integrante, retorna null
        if (contagemPorIntegrante.isEmpty()) {
            return null;
        }

        // 7) encontra o ID do integrante com maior contagem
        Long idMaisUsado = null;
        Long contadorMaximo = 0L;
        for (Map.Entry<Long, Long> entrada : contagemPorIntegrante.entrySet()) {
            Long idAtual = entrada.getKey();
            Long contagemAtual = entrada.getValue();
            if (idMaisUsado == null || contagemAtual > contadorMaximo) {
                idMaisUsado = idAtual;
                contadorMaximo = contagemAtual;
            }
        }

        // 8) recupera e retorna o próprio objeto Integrante
        return integranteRepository.findById(idMaisUsado).orElse(null);
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
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal) {
        List<Time> times = timeRepository.findAll();
        Map<String, Long> contagem = new HashMap<>();

        LocalDate inicio = dataInicial != null ? dataInicial : LocalDate.MIN;
        LocalDate fim    = dataFinal   != null ? dataFinal   : LocalDate.MAX;

        for (Time time : times) {
            LocalDate d = time.getData();
            if (d.isBefore(inicio) || d.isAfter(fim)) {
                continue; // ignora fora do período
            }

            Set<Long> idsNoMesmoTime = new HashSet<>();
            for (Integrante i : time.getIntegrantes()) {
                Long idF = i.getFuncao().getFranquia().getId();
                if (!idsNoMesmoTime.add(idF)) {
                    continue; // já contou esta franquia neste time
                }
                String desc = i.getFuncao().getFranquia().getDescricao();
                contagem.put(desc, contagem.getOrDefault(desc, 0L) + 1);
            }
        }

        return contagem;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal) {
        List<Time> times = timeRepository.findAll();
        Map<String, Long> contagem = new HashMap<>();

        LocalDate inicio = dataInicial != null ? dataInicial : LocalDate.MIN;
        LocalDate fim    = dataFinal   != null ? dataFinal   : LocalDate.MAX;

        for (Time time : times) {
            LocalDate d = time.getData();
            if (d.isBefore(inicio) || d.isAfter(fim)) {
                continue; // ignora fora do período
            }

            // garante que cada função seja contada apenas uma vez por time
            Set<Long> funcoesNoMesmoTime = new HashSet<>();
            for (Integrante i : time.getIntegrantes()) {
                Long idFuncao = i.getFuncao().getId();
                if (!funcoesNoMesmoTime.add(idFuncao)) {
                    continue; // já contou essa função neste time
                }

                String descricaoFuncao = i.getFuncao().getDescricao();
                contagem.put(
                        descricaoFuncao,
                        contagem.getOrDefault(descricaoFuncao, 0L) + 1
                );
            }
        }

        return contagem;
    }

}
