package com.joerison.duxusdesafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/composicao-time")
public class ComposicaoTimeResource {

    @GetMapping
    public ResponseEntity<?> listarTodos()
    {
       return ResponseEntity.ok().build();
    }

}
