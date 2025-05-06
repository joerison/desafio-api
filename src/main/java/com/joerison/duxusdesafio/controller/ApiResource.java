package com.joerison.duxusdesafio.controller;

import com.joerison.duxusdesafio.dto.TimeDTO;
import com.joerison.duxusdesafio.mapper.TimeMapper;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.service.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ApiResource {

    private final ApiService apiService;

    public ApiResource(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("time-da-data")
    public ResponseEntity<?> timeDaData(@RequestParam LocalDate data)
    {
        Time time = apiService.timeDaData(data, null);
        TimeDTO timeDTO = TimeMapper.INSTANCE.toDto(time);
        return ResponseEntity.ok(timeDTO);
    }

    @GetMapping("integrante-mais-usado")
    public ResponseEntity<?> IntegranteMaisUsado()
    {
//        apiService.integranteMaisUsado();
        return ResponseEntity.ok().build();
    }

    @GetMapping("time-mais-comum")
    public ResponseEntity<?> TimeMaisComum()
    {
//        apiService.franquiaMaisFamosa();
        return ResponseEntity.ok().build();
    }

    @GetMapping("funcao-mais-comum")
    public ResponseEntity<?> FuncaoMaisComum()
    {
//        apiService.funcaoMaisComum();
        return ResponseEntity.ok().build();
    }

    @GetMapping("contagem-por-franquia")
    public ResponseEntity<?> ContagemPorFranquia()
    {
//        apiService.contagemPorFranquia();
        return ResponseEntity.ok().build();
    }

    @GetMapping("contagem-por-funcao")
    public ResponseEntity<?> ContagemPorFuncao()
    {
//        apiService.contagemPorFuncao();
        return ResponseEntity.ok().build();
    }

}
