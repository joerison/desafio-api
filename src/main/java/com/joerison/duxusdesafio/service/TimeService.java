package com.joerison.duxusdesafio.service;

import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.dto.TimeDTO;
import com.joerison.duxusdesafio.mapper.IntegranteMapper;
import com.joerison.duxusdesafio.mapper.TimeMapper;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.repository.IntegranteRepository;
import com.joerison.duxusdesafio.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional
    public TimeDTO salvar(TimeDTO timeDTO) {

        Time time = TimeMapper.INSTANCE.toEntity(timeDTO);

        validarData(timeDTO.getData());

        Time timaeSalvo = timeRepository.save(time);

        return TimeMapper.INSTANCE.toDto(timaeSalvo);
    }

    private void validarData(LocalDate data){
        Time timeExistente = timeRepository.findByData(data);

        if (timeExistente != null){
            throw new RuntimeException("Já consta um cadastro de Time para essa data!");
        }
    }

}
