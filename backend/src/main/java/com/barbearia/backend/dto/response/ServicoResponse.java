package com.barbearia.backend.dto.response;

import com.barbearia.backend.model.TipoServico;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

public class ServicoResponse {

    private Long id;
    private TipoServico tipo;
    private String descricao;
    private BigDecimal preco;
    private Integer duracaoMinutos;
}
