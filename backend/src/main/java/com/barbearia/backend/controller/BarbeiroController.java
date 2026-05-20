package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.BarbeiroRequest;
import com.barbearia.backend.dto.response.BarbeiroResponse;
import com.barbearia.backend.service.BarbeiroService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/barbeiros")
@RequiredArgsConstructor
@Tag(name = "Barbeiros", description = "Gerenciamento de barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @PostMapping
    @Operation(summary = "Cadastrar barbeiro",
            description = "Cadastra um novo barbeiro no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Barbeiro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já cadastrado"),
    })
    public ResponseEntity<BarbeiroResponse> cadastrar(
            @RequestBody @Valid BarbeiroRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(barbeiroService.cadastrar(request));
    }

    @GetMapping
    @Operation(summary = "Listar barbeiros ativos",
            description = "Retorna todos os barbeiros ativos no sistema")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<BarbeiroResponse>> listarAtivos() {
        return ResponseEntity.ok(barbeiroService.listarAtivos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar barbeiro por ID",
            description = "Retorna um barbeiro específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Barbeiro encontrado"),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado")
    })
    public ResponseEntity<BarbeiroResponse> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(barbeiroService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar barbeiro",
            description = "Atualiza os dados de um barbeiro existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Barbeiro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid BarbeiroRequest request) {
        return ResponseEntity.ok(barbeiroService.atualizar(id, request));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Inativar barbeiro",
            description = "Realiza soft delete do barbeiro — ele não é removido do banco")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Barbeiro inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        barbeiroService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
