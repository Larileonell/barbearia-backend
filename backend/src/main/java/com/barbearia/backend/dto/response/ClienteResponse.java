package com.barbearia.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClienteResponse {


    private Long id;
    private String nome;
    private String email;
    private String endereco;
    private LocalDateTime criadoEm;
}
