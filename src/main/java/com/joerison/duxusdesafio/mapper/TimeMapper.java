package com.joerison.duxusdesafio.mapper;

import com.joerison.duxusdesafio.dto.TimeDTO;
import com.joerison.duxusdesafio.model.Time;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TimeMapper extends EntityMapper<TimeDTO, Time> {

    TimeMapper INSTANCE = Mappers.getMapper(TimeMapper.class);

    @Override
    Time toEntity(TimeDTO dto);

    @Override
    TimeDTO toDto(Time entity);

}
