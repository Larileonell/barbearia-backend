package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.BarbeiroRequest;
import com.barbearia.backend.dto.response.BarbeiroResponse;
import com.barbearia.backend.service.BarbeiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbeiros")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @PostMapping
    public ResponseEntity<BarbeiroResponse> cadastrar(
            @RequestBody @Valid BarbeiroRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(barbeiroService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroResponse>> listarAtivos() {
        return ResponseEntity.ok(barbeiroService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroResponse> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(barbeiroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid BarbeiroRequest request) {
        return ResponseEntity.ok(barbeiroService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        barbeiroService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
