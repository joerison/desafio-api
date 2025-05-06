package com.joerison.duxusdesafio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "time")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", unique = true, nullable = false)
    private LocalDate data;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "composicao_time",
            joinColumns = @JoinColumn(name = "time_id"),
            inverseJoinColumns = @JoinColumn(name = "integrante_id")
    )
    private List<Integrante> integrantes;

}
