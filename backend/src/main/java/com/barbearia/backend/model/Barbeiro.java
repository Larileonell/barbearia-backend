package com.barbearia.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barbeiros")
public class Barbeiro extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String especialidade;

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;
}



