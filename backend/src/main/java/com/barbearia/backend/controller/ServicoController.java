package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.ServicoRequest;
import com.barbearia.backend.dto.response.ServicoResponse;
import com.barbearia.backend.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponse> cadastrar(
            @RequestBody @Valid ServicoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(servicoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponse>> listarTodos() {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

}
