package com.barbearia.backend.dto.response;

import com.barbearia.backend.model.StatusAgendamento;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoResponse {

    private Long id;
    private LocalDate data;
    private LocalTime horario;
    private StatusAgendamento status;
    private String observacao;
    private String nomeCliente;
    private String nomeBarbeiro;
    private String tipoServico;
    private String criadoEm;
}

