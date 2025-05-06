package com.joerison.duxusdesafio.repository;

import com.joerison.duxusdesafio.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TimeRepository extends JpaRepository<Time, Long> {

    Time findByData(LocalDate data);

}
