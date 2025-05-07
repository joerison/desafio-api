package com.joerison.duxusdesafio.mapper;

import com.joerison.duxusdesafio.dto.FuncaoDTO;
import com.joerison.duxusdesafio.model.Funcao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FuncaoMapper extends EntityMapper<FuncaoDTO, Funcao> {

    FuncaoMapper INSTANCE = Mappers.getMapper(FuncaoMapper.class);

    @Override
    Funcao toEntity(FuncaoDTO dto);

    @Override
    FuncaoDTO toDto(Funcao entity);

}
