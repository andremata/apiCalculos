package com.andre.apicalculos.controller;

import com.andre.apicalculos.entity.dto.EstatisticaRecordDTO;
import com.andre.apicalculos.service.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatisticas de transações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação obtidas com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<EstatisticaRecordDTO> obterEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {

        return ResponseEntity.ok(estatisticaService.calcularEstatisticas(intervaloBusca));
    }
}
