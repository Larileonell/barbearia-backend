package com.barbearia.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BarbeiroResponse {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String especialidade;
    private Boolean ativo;
    private LocalDateTime criadoEm;
}
