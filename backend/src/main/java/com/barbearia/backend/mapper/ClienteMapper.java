package com.barbearia.backend.mapper;

import com.barbearia.backend.dto.request.ClienteRequest;
import com.barbearia.backend.dto.response.ClienteResponse;
import com.barbearia.backend.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(@org.checkerframework.checker.nullness.qual.MonotonicNonNull ClienteRequest request) {
        return Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .cpf(request.getCpf())
                .endereco(request.getEndereco())
                .build();
    }
    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .endereco(cliente.getEndereco())
                .criadoEm(cliente.getCreateEm())
                .build();
    }
}
