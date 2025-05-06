package com.joerison.duxusdesafio.model;

import com.joerison.duxusdesafio.dto.FranquiaDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "funcao")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "franquia_id", nullable = false)
    private Franquia franquia;

}
