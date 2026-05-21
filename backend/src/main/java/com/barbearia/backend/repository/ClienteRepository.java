package com.barbearia.backend.repository;

import com.barbearia.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    // Utilizado na autenticação JWT - implementação futura
    Optional<Cliente> findByEmail(String email);
    // Utilizado na autenticação JWT - implementação futura
    Optional<Cliente> findByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
