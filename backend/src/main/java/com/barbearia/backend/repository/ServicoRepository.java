package com.barbearia.backend.repository;

import com.barbearia.backend.model.Servico;
import com.barbearia.backend.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository  extends JpaRepository<Servico,Long> {
    List<Servico> findByTipo(TipoServico tipo);
}
