package com.barbearia.backend.mapper;

import com.barbearia.backend.dto.response.ServicoResponse;
import com.barbearia.backend.model.Servico;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {
    public Servico toEntity(Servico request){
        return Servico.builder()
                .tipo(request.getTipo())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .duracaoMinutos(request.getDuracaoMinutos())
                .build();
    }
    public ServicoResponse toResponse(Servico servico) {
        return ServicoResponse.builder()
                .id(servico.getId())
                .tipo(servico.getTipo())
                .descricao(servico.getDescricao())
                .preco(servico.getPreco())
                .duracaoMinutos(servico.getDuracaoMinutos())
                .build();
    }
}
