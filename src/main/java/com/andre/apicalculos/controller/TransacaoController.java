package com.andre.apicalculos.controller;

import com.andre.apicalculos.entity.dto.TransacaoRecordDTO;
import com.andre.apicalculos.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint responsável por adicionar transações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação inserida com sucesso."),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRecordDTO transacao) {
        transacaoService.adicionarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por excluir transações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação excluída com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<Void> excluirTransacoes() {
        transacaoService.excluirTransacoes();
        return ResponseEntity.ok().build();
    }
}
