package com.barbearia.backend.repository;

import com.barbearia.backend.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro,Long> {

    Optional<Barbeiro> findByEmail (String email);
    List<Barbeiro>  findByAtivoTrue();
    boolean existsByEmail(String email);
}
