package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.mapper.IntegranteMapper;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.repository.IntegranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteService {

    private final IntegranteRepository integranteRepository;

    public IntegranteService(IntegranteRepository integranteRepository) {
        this.integranteRepository = integranteRepository;
    }

    @Transactional
    public IntegranteDTO salvar(IntegranteDTO integranteDTO) {

        Integrante integrante = IntegranteMapper.INSTANCE.toEntity(integranteDTO);

        Integrante integrandeSalvo = integranteRepository.save(integrante);

        return IntegranteMapper.INSTANCE.toDto(integrandeSalvo);
    }

    public List<Integrante> listar(){
        return this.integranteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
