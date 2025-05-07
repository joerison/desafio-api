package com.joerison.duxusdesafio.controller;

import com.joerison.duxusdesafio.dto.FranquiaDTO;
import com.joerison.duxusdesafio.dto.FuncaoDTO;
import com.joerison.duxusdesafio.dto.IntegranteDTO;
import com.joerison.duxusdesafio.dto.TimeDTO;
import com.joerison.duxusdesafio.mapper.FranquiaMapper;
import com.joerison.duxusdesafio.mapper.FuncaoMapper;
import com.joerison.duxusdesafio.mapper.IntegranteMapper;
import com.joerison.duxusdesafio.mapper.TimeMapper;
import com.joerison.duxusdesafio.model.Franquia;
import com.joerison.duxusdesafio.model.Funcao;
import com.joerison.duxusdesafio.model.Integrante;
import com.joerison.duxusdesafio.model.Time;
import com.joerison.duxusdesafio.service.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

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
    public ResponseEntity<?> IntegranteMaisUsado(@RequestParam (required = false) LocalDate dataInicio,
                                                 @RequestParam (required = false) LocalDate dataFim)
    {
        Integrante integrante = apiService.integranteMaisUsado(dataInicio, dataFim);
        IntegranteDTO integranteDTO = IntegranteMapper.INSTANCE.toDto(integrante);
        return ResponseEntity.ok(integranteDTO);
    }

    @GetMapping("time-mais-comum")
    public ResponseEntity<?> TimeMaisComum(@RequestParam (required = false) LocalDate dataInicio,
                                           @RequestParam (required = false) LocalDate dataFim)
    {
        Time time = apiService.integrantesDoTimeMaisComum(dataInicio, dataFim);
        TimeDTO timeDTO = TimeMapper.INSTANCE.toDto(time);
        return ResponseEntity.ok(timeDTO);
    }

    @GetMapping("funcao-mais-comum")
    public ResponseEntity<?> FuncaoMaisComum(@RequestParam (required = false) LocalDate dataInicio,
                                             @RequestParam (required = false) LocalDate dataFim)
    {
        Funcao funcao = apiService.funcaoMaisComum(dataInicio, dataFim);
        FuncaoDTO funcaoDTO = FuncaoMapper.INSTANCE.toDto(funcao);
        return ResponseEntity.ok(funcaoDTO);
    }

    @GetMapping("franquia-mais-famosa")
    public ResponseEntity<?> FranquiaMaisFamosa(@RequestParam (required = false) LocalDate dataInicio,
                                                @RequestParam (required = false) LocalDate dataFim)
    {
        Franquia franquia = apiService.franquiaMaisFamosa(dataInicio, dataFim);
        FranquiaDTO franquiaDTO = FranquiaMapper.INSTANCE.toDto(franquia);
        return ResponseEntity.ok(franquiaDTO);
    }

    @GetMapping("contagem-por-franquia")
    public ResponseEntity<?> ContagemPorFranquia(@RequestParam (required = false) LocalDate dataInicio,
                                                 @RequestParam (required = false) LocalDate dataFim)
    {
        Map<String, Long> contagemPorFranquia = apiService.contagemPorFranquia(dataInicio, dataFim);
        return ResponseEntity.ok(contagemPorFranquia);
    }

    @GetMapping("contagem-por-funcao")
    public ResponseEntity<?> ContagemPorFuncao(@RequestParam (required = false) LocalDate dataInicio,
                                               @RequestParam (required = false) LocalDate dataFim)
    {
        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicio, dataFim);
        return ResponseEntity.ok(contagemPorFuncao);
    }

}
