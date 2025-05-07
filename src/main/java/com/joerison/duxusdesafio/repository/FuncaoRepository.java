package com.joerison.duxusdesafio.repository;

import com.joerison.duxusdesafio.model.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

    List<Funcao> findByFranquiaId(Long franquiaId);

}
