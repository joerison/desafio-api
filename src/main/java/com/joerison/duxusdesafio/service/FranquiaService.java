package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.model.Franquia;
import com.joerison.duxusdesafio.repository.FranquiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranquiaService {

    private final FranquiaRepository franquiaRepository;

    public FranquiaService(FranquiaRepository franquiaRepository) {
        this.franquiaRepository = franquiaRepository;
    }

    public List<Franquia> listar(){
        return this.franquiaRepository.findAll();
    }

}
