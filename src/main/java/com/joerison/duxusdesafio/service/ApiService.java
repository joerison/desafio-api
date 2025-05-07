package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.model.Franquia;
import com.joerison.duxusdesafio.model.Funcao;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.repository.FranquiaRepository;
import com.joerison.duxusdesafio.repository.FuncaoRepository;
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
    private final FranquiaRepository franquiaRepository;
    private final FuncaoRepository funcaoRepository;

    public ApiService(TimeRepository timeRepository,
                      IntegranteRepository integranteRepository,
                      FranquiaRepository franquiaRepository,
                      FuncaoRepository funcaoRepository) {
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
        this.franquiaRepository = franquiaRepository;
        this.funcaoRepository = funcaoRepository;
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
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
        List<Time> todosOsTimes = timeRepository.findAll();

        LocalDate inicio = (dataInicial != null ? dataInicial : LocalDate.MIN);
        LocalDate fim = (dataFinal != null ? dataFinal : LocalDate.MAX);

        Map<Long, Long> contagemPorIntegrante = new HashMap<>();

        for (Time time : todosOsTimes) {
            LocalDate dataDoTime = time.getData();
            if (dataDoTime.isBefore(inicio) || dataDoTime.isAfter(fim)) {
                continue;
            }

            Set<Long> integrantesContadosNoMesmoTime = new HashSet<>();
            for (Integrante integrante : time.getIntegrantes()) {
                Long idIntegrante = integrante.getId();
                if (!integrantesContadosNoMesmoTime.add(idIntegrante)) {
                    continue;
                }
                contagemPorIntegrante.put(idIntegrante, contagemPorIntegrante.getOrDefault(idIntegrante, 0L) + 1);
            }
        }

        if (contagemPorIntegrante.isEmpty()) {
            return null;
        }

        Long idMaisUsado = null;
        Long contadorMaximo = 0L;
        for (Map.Entry<Long, Long> entrada : contagemPorIntegrante.entrySet()) {
            if (idMaisUsado == null || entrada.getValue() > contadorMaximo) {
                idMaisUsado = entrada.getKey();
                contadorMaximo = entrada.getValue();
            }
        }

        return integranteRepository.findById(idMaisUsado).orElse(null);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public Time integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        LocalDate inicio = (dataInicial != null ? dataInicial : LocalDate.MIN);
        LocalDate fim = (dataFinal != null ? dataFinal : LocalDate.MAX);

        Map<String, Long> contagemPorComposicao = new HashMap<>();
        Map<String, Time> primeiroTimePorComposicao = new HashMap<>();

        for (Time time : listaTodosOsTimes) {
            LocalDate dataDoTime = time.getData();
            if (dataDoTime.isBefore(inicio) || dataDoTime.isAfter(fim)) {
                continue;
            }

            if (time.getIntegrantes() == null || time.getIntegrantes().isEmpty()) {
                continue;
            }

            List<Long> listaIds = new ArrayList<>();
            for (Integrante integrante : time.getIntegrantes()) {
                listaIds.add(integrante.getId());
            }
            Collections.sort(listaIds);

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < listaIds.size(); i++) {
                if (i > 0) builder.append(",");
                builder.append(listaIds.get(i));
            }
            String chaveComposicao = builder.toString();

            if (!contagemPorComposicao.containsKey(chaveComposicao)) {
                contagemPorComposicao.put(chaveComposicao, 1L);
                primeiroTimePorComposicao.put(chaveComposicao, time);
            } else {
                contagemPorComposicao.put(chaveComposicao, contagemPorComposicao.get(chaveComposicao) + 1);
            }
        }

        if (contagemPorComposicao.isEmpty()) {
            return null;
        }

        String chaveMaisComum = null;
        long maiorQtd = 0;
        for (Map.Entry<String, Long> e : contagemPorComposicao.entrySet()) {
            if (chaveMaisComum == null || e.getValue() > maiorQtd) {
                chaveMaisComum = e.getKey();
                maiorQtd = e.getValue();
            }
        }

        return primeiroTimePorComposicao.get(chaveMaisComum);
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public Funcao funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        LocalDate inicio = (dataInicial != null ? dataInicial : LocalDate.MIN);
        LocalDate fim = (dataFinal != null ? dataFinal : LocalDate.MAX);

        Map<Long, Long> contagemPorIdDeFuncao = new HashMap<>();

        for (Time time : listaTodosOsTimes) {
            LocalDate dataDoTime = time.getData();
            if (dataDoTime.isBefore(inicio) || dataDoTime.isAfter(fim)) {
                continue;
            }

            Set<Long> idsDeFuncaoNoMesmoTime = new HashSet<>();
            for (Integrante integrante : time.getIntegrantes()) {
                Long idDeFuncao = integrante.getFuncao().getId();
                if (!idsDeFuncaoNoMesmoTime.add(idDeFuncao)) {
                    continue;
                }
                contagemPorIdDeFuncao.put(idDeFuncao, contagemPorIdDeFuncao.getOrDefault(idDeFuncao, 0L) + 1);
            }
        }

        if (contagemPorIdDeFuncao.isEmpty()) {
            return null;
        }

        Long idDaFuncaoMaisComum = null;
        Long maiorQuantidade = 0L;
        for (Map.Entry<Long, Long> entrada : contagemPorIdDeFuncao.entrySet()) {
            if (idDaFuncaoMaisComum == null || entrada.getValue() > maiorQuantidade) {
                idDaFuncaoMaisComum = entrada.getKey();
                maiorQuantidade = entrada.getValue();
            }
        }

        return funcaoRepository.findById(idDaFuncaoMaisComum).orElse(null);
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public Franquia franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        LocalDate inicio = (dataInicial != null ? dataInicial : LocalDate.MIN);
        LocalDate fim = (dataFinal != null ? dataFinal : LocalDate.MAX);

        Map<Long, Long> contagemPorIdFranquia = new HashMap<>();

        for (Time time : listaTodosOsTimes) {
            LocalDate dataDoTime = time.getData();
            if (dataDoTime.isBefore(inicio) || dataDoTime.isAfter(fim)) {
                continue;
            }

            Set<Long> franquiasNoMesmoTime = new HashSet<>();
            for (Integrante integrante : time.getIntegrantes()) {
                Long idFranquia = integrante.getFuncao().getFranquia().getId();
                if (!franquiasNoMesmoTime.add(idFranquia)) {
                    continue;
                }
                contagemPorIdFranquia.put(idFranquia, contagemPorIdFranquia.getOrDefault(idFranquia, 0L) + 1);
            }
        }

        if (contagemPorIdFranquia.isEmpty()) {
            return null;
        }

        Long idMaisFamosa = null;
        Long maiorContagem = 0L;
        for (Map.Entry<Long, Long> entrada : contagemPorIdFranquia.entrySet()) {
            if (idMaisFamosa == null || entrada.getValue() > maiorContagem) {
                idMaisFamosa = entrada.getKey();
                maiorContagem = entrada.getValue();
            }
        }

        return franquiaRepository.findById(idMaisFamosa).orElse(null);
    }

    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal) {
        List<Time> times = timeRepository.findAll();
        Map<String, Long> contagem = new HashMap<>();

        LocalDate inicio = dataInicial != null ? dataInicial : LocalDate.MIN;
        LocalDate fim = dataFinal != null ? dataFinal : LocalDate.MAX;

        for (Time time : times) {
            LocalDate d = time.getData();
            if (d.isBefore(inicio) || d.isAfter(fim)) {
                continue;
            }

            Set<Long> idsNoMesmoTime = new HashSet<>();
            for (Integrante i : time.getIntegrantes()) {
                Long idF = i.getFuncao().getFranquia().getId();
                if (!idsNoMesmoTime.add(idF)) {
                    continue;
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
        LocalDate fim = dataFinal != null ? dataFinal : LocalDate.MAX;

        for (Time time : times) {
            LocalDate d = time.getData();
            if (d.isBefore(inicio) || d.isAfter(fim)) {
                continue;
            }

            Set<Long> funcoesNoMesmoTime = new HashSet<>();
            for (Integrante i : time.getIntegrantes()) {
                Long idFuncao = i.getFuncao().getId();
                if (!funcoesNoMesmoTime.add(idFuncao)) {
                    continue;
                }

                String descricaoFuncao = i.getFuncao().getDescricao();
                contagem.put(descricaoFuncao, contagem.getOrDefault(descricaoFuncao, 0L) + 1);
            }
        }

        return contagem;
    }
}
