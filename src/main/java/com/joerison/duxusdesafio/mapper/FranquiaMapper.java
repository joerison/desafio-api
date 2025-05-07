package com.joerison.duxusdesafio.mapper;

import com.joerison.duxusdesafio.dto.FranquiaDTO;
import com.joerison.duxusdesafio.model.Franquia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FranquiaMapper extends EntityMapper<FranquiaDTO, Franquia> {

    FranquiaMapper INSTANCE = Mappers.getMapper(FranquiaMapper.class);

    @Override
    Franquia toEntity(FranquiaDTO dto);

    @Override
    FranquiaDTO toDto(Franquia entity);

}
