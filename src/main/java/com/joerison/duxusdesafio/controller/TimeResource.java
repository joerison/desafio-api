package com.joerison.duxusdesafio.controller;

import com.joerison.duxusdesafio.dto.TimeDTO;
import com.joerison.duxusdesafio.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/time")
public class TimeResource {

    private final TimeService timeService;

    public TimeResource(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TimeDTO timeDTO)
    {

        TimeDTO dtoSalvo = this.timeService.salvar(timeDTO);

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
       return ResponseEntity.ok().build();
    }

}
