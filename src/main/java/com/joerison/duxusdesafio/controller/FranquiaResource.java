package com.joerison.duxusdesafio.controller;

import com.joerison.duxusdesafio.dto.FranquiaDTO;
import com.joerison.duxusdesafio.dto.FuncaoDTO;
import com.joerison.duxusdesafio.mapper.FranquiaMapper;
import com.joerison.duxusdesafio.mapper.FuncaoMapper;
import com.joerison.duxusdesafio.model.Franquia;
import com.joerison.duxusdesafio.model.Funcao;
import com.joerison.duxusdesafio.service.FranquiaService;
import com.joerison.duxusdesafio.service.FuncaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franquia")
public class FranquiaResource {

    private final FuncaoService funcaoService;
    private final FranquiaService franquiaService;

    public FranquiaResource(FuncaoService funcaoService,
                            FranquiaService franquiaService) {
        this.funcaoService = funcaoService;
        this.franquiaService = franquiaService;
    }

    @GetMapping
    public ResponseEntity<?> listarTodas()
    {

        List<Franquia> lista = this.franquiaService.listar();
        List<FranquiaDTO> listaDTO = FranquiaMapper.INSTANCE.toDto(lista);

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}/funcoes")
    public ResponseEntity<?> listarFuncoes(@PathVariable Long id)
    {

        List<Funcao> lista = this.funcaoService.listar(id);
        List<FuncaoDTO> listaDTO = FuncaoMapper.INSTANCE.toDto(lista);

        return ResponseEntity.ok(listaDTO);
    }

}
