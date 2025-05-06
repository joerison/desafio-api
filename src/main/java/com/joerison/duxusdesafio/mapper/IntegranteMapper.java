package com.joerison.duxusdesafio.mapper;

import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.model.Integrante;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IntegranteMapper extends EntityMapper<IntegranteDTO, Integrante> {

    IntegranteMapper INSTANCE = Mappers.getMapper(IntegranteMapper.class);

    @Override
    Integrante toEntity(IntegranteDTO dto);

    @Override
    IntegranteDTO toDto(Integrante entity);

}
