package com.joerison.duxusdesafio.controller;

import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.mapper.IntegranteMapper;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.service.IntegranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/integrante")
public class IntegranteResource {

    private final IntegranteService integranteService;

    public IntegranteResource(IntegranteService integranteService) {
        this.integranteService = integranteService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody IntegranteDTO integranteDTO)
    {

        IntegranteDTO dtoSalvo = this.integranteService.salvar(integranteDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dtoSalvo.getId())
                .toUri();

        return dtoSalvo.getId() != null ?
                ResponseEntity.created(uri).body(dtoSalvo) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> listarTodos()
    {

        List<Integrante> lista = this.integranteService.listar();
        List<IntegranteDTO> listaDTO = IntegranteMapper.INSTANCE.toDto(lista);

        return ResponseEntity.ok(listaDTO);
    }

}
