package com.barbearia.backend.mapper;

import com.barbearia.backend.dto.request.BarbeiroRequest;
import com.barbearia.backend.dto.response.BarbeiroResponse;
import com.barbearia.backend.model.Barbeiro;
import org.springframework.stereotype.Component;

@Component
public class BarbeiroMapper {
    public Barbeiro toEntity(@org.checkerframework.checker.nullness.qual.MonotonicNonNull BarbeiroRequest request) {
        return Barbeiro.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .especialidade(request.getEspecialidade())
                .ativo(true)
                .build();
    }
    public BarbeiroResponse toResponse(Barbeiro barbeiro) {
        return BarbeiroResponse.builder()
                .id(barbeiro.getId())
                .nome(barbeiro.getNome())
                .email(barbeiro.getEmail())
                .telefone(barbeiro.getTelefone())
                .especialidade(barbeiro.getEspecialidade())
                .ativo(barbeiro.getAtivo())
                .criadoEm(barbeiro.getCreateEm())
                .build();
    }
}