package com.joerison.duxusdesafio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "composicao_time")
public class ComposicaoTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_integrante", nullable = false)
    private Integrante integrante;

}
