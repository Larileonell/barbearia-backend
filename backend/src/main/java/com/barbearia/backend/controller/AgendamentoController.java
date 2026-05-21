package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.AgendamentoRequest;
import com.barbearia.backend.dto.response.AgendamentoResponse;
import com.barbearia.backend.model.StatusAgendamento;
import com.barbearia.backend.service.AgendamentoService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Gerenciamento de agendamentos da barbearia")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @Operation(summary = "Criar agendamento",
            description = "Cria um novo agendamento validando disponibilidade do barbeiro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou horário indisponível"),
            @ApiResponse(responseCode = "404", description = "Barbeiro, cliente ou serviço não encontrado")
    })
    public ResponseEntity<AgendamentoResponse> criar(
            @RequestBody @Valid AgendamentoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(agendamentoService.criarAgendamento(request));
    }

    @GetMapping
    @Operation(summary = "Listar agendamentos",
            description = "Retorna todos os agendamentos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<AgendamentoResponse>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar agendamentos por cliente",
            description = "Retorna todos os agendamentos de um cliente específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<List<AgendamentoResponse>> listarPorCliente(
            @Parameter(description = "ID do cliente") @PathVariable Long clienteId) {
        return ResponseEntity.ok(
                agendamentoService.listarPorCliente(clienteId));
    }

    @GetMapping("/barbeiro/{barbeiroId}")
    @Operation(summary = "Listar agendamentos por barbeiro",
            description = "Retorna todos os agendamentos de um barbeiro específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado")
    })
    public ResponseEntity<List<AgendamentoResponse>> listarPorBarbeiro(
            @Parameter(description = "ID do barbeiro") @PathVariable Long barbeiroId) {
        return ResponseEntity.ok(
                agendamentoService.listarPorBarbeiro(barbeiroId));
    }

    @PatchMapping("/{id}/confirmar")
    @Operation(summary = "Confirmar agendamento",
            description = "Confirma um agendamento com status AGENDADO")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento confirmado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Agendamento não pode ser confirmado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public ResponseEntity<AgendamentoResponse> confirmar(
            @Parameter(description = "ID do agendamento") @PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.confirmar(id));
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar agendamento",
            description = "Cancela um agendamento que ainda não foi cancelado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Agendamento já está cancelado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public ResponseEntity<AgendamentoResponse> cancelar(
            @Parameter(description = "ID do agendamento") @PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.cancelar(id));
    }
    @GetMapping("/status/{status}")
    @Operation(summary = "Listar agendamentos por status",
            description = "Retorna agendamentos filtrados por status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status inválido")
    })
    public ResponseEntity<List<AgendamentoResponse>> listarPorStatus(
            @Parameter(description = "Status do agendamento")
            @PathVariable StatusAgendamento status) {
        return ResponseEntity.ok(agendamentoService.listarPorStatus(status));
    }

    @GetMapping("/periodo")
    @Operation(summary = "Listar agendamentos por período",
            description = "Retorna agendamentos entre duas datas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Período inválido")
    })
    public ResponseEntity<List<AgendamentoResponse>> listarPorPeriodo(
            @Parameter(description = "Data início (yyyy-MM-dd)")
            @RequestParam LocalDate dataInicio,
            @Parameter(description = "Data fim (yyyy-MM-dd)")
            @RequestParam LocalDate dataFim) {
        return ResponseEntity.ok(
                agendamentoService.listarPorPeriodo(dataInicio, dataFim));
    }
}