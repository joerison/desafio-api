package com.joerison.duxusdesafio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntegranteDTO {

    private Long id;

    private String nome;
    private FuncaoDTO funcao;

}
