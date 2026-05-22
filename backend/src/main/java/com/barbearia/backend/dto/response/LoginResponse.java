package com.barbearia.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;
    private String nome;
    private String email;
    private String perfil;
}
