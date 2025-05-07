package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.mapper.IntegranteMapper;
import com.joerison.duxusdesafio.model.Funcao;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.repository.FuncaoRepository;
import com.joerison.duxusdesafio.repository.IntegranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncaoService {

    private final FuncaoRepository funcaoRepository;

    public FuncaoService(FuncaoRepository funcaoRepository) {
        this.funcaoRepository = funcaoRepository;
    }

    public List<Funcao> listar(Long franquiaId){
        return this.funcaoRepository.findByFranquiaId(franquiaId);
    }

}
