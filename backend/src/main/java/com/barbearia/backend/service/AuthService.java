package com.barbearia.backend.service;

import com.barbearia.backend.dto.request.LoginRequest;
import com.barbearia.backend.dto.response.LoginResponse;
import com.barbearia.backend.model.Usuario;
import com.barbearia.backend.repository.UsuarioRepository;
import com.barbearia.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );

        Usuario usuario = usuarioRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.gerarToken(usuario);

        return LoginResponse.builder()
                .token(token)
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil().name())
                .build();
    }


}
