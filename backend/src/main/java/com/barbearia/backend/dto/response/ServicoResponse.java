package com.barbearia.backend.dto.response;

import com.barbearia.backend.model.TipoServico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
public class ServicoResponse {

    private Long id;
    private TipoServico tipo;
    private String descricao;
    private BigDecimal preco;
    private Integer duracaoMinutos;
}
