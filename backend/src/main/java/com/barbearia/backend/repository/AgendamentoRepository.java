package com.barbearia.backend.repository;

import com.barbearia.backend.model.Agendamento;
import com.barbearia.backend.model.StatusAgendamento;
import com.barbearia.backend.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByBarbeiroIdAndData(Long barbeiroId, LocalDate data);

    List<Agendamento> findByClienteId(Long clienteId);

    List<Agendamento> findByStatus(StatusAgendamento status);

    List<Agendamento> findByDataBetween(LocalDate inicio, LocalDate fim);

    @Query("""
        SELECT COUNT(a) > 0 FROM Agendamento a
        WHERE a.barbeiro.id = :barbeiroId
        AND a.data = :data
        AND a.horario = :horario
        AND a.status <> :status
    """)
    boolean existeConflito(
            @Param("barbeiroId") Long barbeiroId,
            @Param("data") LocalDate data,
            @Param("horario") LocalTime horario,
            @Param("status") StatusAgendamento status
    );

}
