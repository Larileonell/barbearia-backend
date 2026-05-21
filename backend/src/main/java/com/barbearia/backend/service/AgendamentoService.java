package com.barbearia.backend.service;

import com.barbearia.backend.dto.request.AgendamentoRequest;
import com.barbearia.backend.dto.response.AgendamentoResponse;
import com.barbearia.backend.exception.RegraNegocioException;
import com.barbearia.backend.exception.ResourceNotFoundException;
import com.barbearia.backend.mapper.AgendamentoMapper;
import com.barbearia.backend.model.*;
import com.barbearia.backend.repository.AgendamentoRepository;
import com.barbearia.backend.repository.BarbeiroRepository;
import com.barbearia.backend.repository.ClienteRepository;
import com.barbearia.backend.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoMapper agendamentoMapper;

    private static final LocalTime HORARIO_ABERTURA = LocalTime.of(8, 0);
    private static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(18, 0);

    private void validarConflitoDeHorario(AgendamentoRequest request) {
        boolean temConflito = agendamentoRepository.existeConflito(
                request.getBarbeiroId(),
                request.getData(),
                request.getHorario(),
                StatusAgendamento.CANCELADO
        );

        if (temConflito) {
            throw new RegraNegocioException(
                    "Este horário já está ocupado para o barbeiro selecionado");
        }
    }

    private Agendamento buscarOuLancarExcecao(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Agendamento não encontrado com id: " + id));
    }

    private Cliente buscarCliente(Long id) {
        return clienteRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente não encontrado com id: " + id));
    }

    private Servico buscarServico(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Serviço não encontrado com id: " + id));
    }
    private Barbeiro buscarBarbeiro(Long id) {
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Barbeiro não encontrado com id: " + id));
    }

    private void validarBarbeiroAtivo(Barbeiro barbeiro) {
        if (!barbeiro.getAtivo()) {
            throw new RegraNegocioException(
                    "Barbeiro inativo não pode receber agendamentos");
        }
    }

    private void validarHorarioFuncionamento(LocalTime horario) {
        if (horario.isBefore(HORARIO_ABERTURA) ||
                horario.isAfter(HORARIO_FECHAMENTO)) {
            throw new RegraNegocioException(
                    "Horário fora do funcionamento. Atendemos das 08:00 às 18:00");
        }
    }


    public AgendamentoResponse criarAgendamento(AgendamentoRequest request) {
        Barbeiro barbeiro = buscarBarbeiro(request.getBarbeiroId());
        Cliente cliente = buscarCliente(request.getClienteId());
        Servico servico = buscarServico(request.getServicoId());
        validarBarbeiroAtivo(barbeiro);
        validarHorarioFuncionamento(request.getHorario());
        validarConflitoDeHorario(request);

        Agendamento agendamento = Agendamento.builder()
                .data(request.getData())
                .horario(request.getHorario())
                .status(StatusAgendamento.AGENDADO)
                .observacao(request.getObservacao())
                .barbeiro(barbeiro)
                .cliente(cliente)
                .servico(servico)
                .build();

        return agendamentoMapper.toResponse(
                agendamentoRepository.save(agendamento));
    }
    public List<AgendamentoResponse> listarTodos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(agendamentoMapper::toResponse)
                .toList();
    }

    public List<AgendamentoResponse> listarPorCliente(Long clienteId) {
        buscarCliente(clienteId);
        return agendamentoRepository.findByClienteId(clienteId)
                .stream()
                .map(agendamentoMapper::toResponse)
                .toList();
    }

    public List<AgendamentoResponse> listarPorBarbeiro(Long barbeiroId) {
        buscarBarbeiro(barbeiroId);
        return agendamentoRepository.findByBarbeiroIdAndData(
                        barbeiroId, null)
                .stream()
                .map(agendamentoMapper::toResponse)
                .toList();
    }

    public AgendamentoResponse confirmar(Long id) {
        Agendamento agendamento = buscarOuLancarExcecao(id);

        if (agendamento.getStatus() != StatusAgendamento.AGENDADO) {
            throw new RegraNegocioException(
                    "Apenas agendamentos com status AGENDADO podem ser confirmados");
        }

        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        return agendamentoMapper.toResponse(
                agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponse cancelar(Long id) {
        Agendamento agendamento = buscarOuLancarExcecao(id);

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new RegraNegocioException(
                    "Este agendamento já está cancelado");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return agendamentoMapper.toResponse(
                agendamentoRepository.save(agendamento));
    }
    public List<AgendamentoResponse> listarPorStatus(StatusAgendamento status) {
        return agendamentoRepository.findByStatus(status)
                .stream()
                .map(agendamentoMapper::toResponse)
                .toList();
    }

    public List<AgendamentoResponse> listarPorPeriodo(
            LocalDate dataInicio, LocalDate dataFim) {

        if (dataInicio.isAfter(dataFim)) {
            throw new RegraNegocioException(
                    "Data início não pode ser maior que data fim");
        }

        return agendamentoRepository.findByDataBetween(dataInicio, dataFim)
                .stream()
                .map(agendamentoMapper::toResponse)
                .toList();
    }
}

