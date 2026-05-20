package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.AgendamentoRequest;
import com.barbearia.backend.dto.response.AgendamentoResponse;
import com.barbearia.backend.model.Agendamento;
import com.barbearia.backend.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criarAgendamento(
            @RequestBody @Valid AgendamentoRequest agendamento) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(agendamentoService.criarAgendamento(agendamento));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> listarTodosAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoResponse>> listarPorClienteId(
            @PathVariable Long clienteId) {
        return ResponseEntity.ok(agendamentoService.listarPorCliente(clienteId));
    }

    @GetMapping("/barbeiro/{barbeiroId}")
    public ResponseEntity<List<AgendamentoResponse>> listarPorBarbeiroId(
            @PathVariable Long barbeiroId) {
        return ResponseEntity.ok(agendamentoService.listarPorCliente(barbeiroId));
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<AgendamentoResponse> confirmar(
            @PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.confirmar(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<AgendamentoResponse> cancelar(
            @PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.cancelar(id));
    }

}
