package com.barbearia.backend.controller;

import com.barbearia.backend.dto.request.LoginRequest;
import com.barbearia.backend.dto.response.LoginResponse;
import com.barbearia.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de login e autenticação")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login",
            description = "Autentica o usuário e retorna o token JWT")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
