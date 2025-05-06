package com.joerison.duxusdesafio.mapper;

import com.joerison.duxusdesafio.dto.ComposicaoTimeDTO;
import com.joerison.duxusdesafio.model.ComposicaoTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComposicaoTimeMapper extends EntityMapper<ComposicaoTimeDTO, ComposicaoTime> {

    ComposicaoTimeMapper INSTANCE = Mappers.getMapper(ComposicaoTimeMapper.class);

    @Override
    ComposicaoTime toEntity(ComposicaoTimeDTO dto);

    @Override
    ComposicaoTimeDTO toDto(ComposicaoTime entity);

}
