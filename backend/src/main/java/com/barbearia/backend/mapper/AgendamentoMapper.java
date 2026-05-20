package com.barbearia.backend.mapper;

import com.barbearia.backend.dto.response.AgendamentoResponse;
import com.barbearia.backend.model.Agendamento;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {
    public AgendamentoResponse toResponse(Agendamento agendamento) {
        return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .data(agendamento.getData())
                .horario(agendamento.getHorario())
                .status(agendamento.getStatus())
                .observacao(agendamento.getObservacao())
                .nomeCliente(agendamento.getCliente().getNome())
                .nomeBarbeiro(agendamento.getBarbeiro().getNome())
                .tipoServico(agendamento.getServico().getTipo().name())
                .criadoEm(agendamento.getCreateEm().toString())
                .build();
    }
}
