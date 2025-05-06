package com.joerison.duxusdesafio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO {

    private Long id;

    private LocalDate data;

    private List<IntegranteDTO> integrantes;

}
