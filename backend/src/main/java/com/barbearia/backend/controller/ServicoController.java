package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.ServicoRequest;
import com.barbearia.backend.dto.response.ServicoResponse;
import com.barbearia.backend.model.TipoServico;
import com.barbearia.backend.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
@Tag(name = "Serviços", description = "Gerenciamento de serviços da barbearia")
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    @Operation(summary = "Cadastrar serviço",
            description = "Cadastra um novo serviço no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Serviço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ServicoResponse> cadastrar(
            @RequestBody @Valid ServicoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(servicoService.cadastrar(request));
    }

    @GetMapping
    @Operation(summary = "Listar serviços",
            description = "Retorna todos os serviços disponíveis")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<ServicoResponse>> listarTodos() {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço por ID",
            description = "Retorna um serviço específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Serviço encontrado"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    public ResponseEntity<ServicoResponse> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }
    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Listar serviços por tipo",
            description = "Retorna serviços filtrados por tipo")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<ServicoResponse>> listarPorTipo(
            @Parameter(description = "Tipo do serviço")
            @PathVariable TipoServico tipo) {
        return ResponseEntity.ok(servicoService.listarPorTipo(tipo));
    }

}
