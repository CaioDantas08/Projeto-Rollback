package com.rollback.api_alunos.model;

import com.rollback.api_alunos.model.enums.NivelRisco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perfis_risco")
@Getter
@Setter
@NoArgsConstructor
public class PerfilRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score_risco", nullable = false)
    private double scoreRisco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelRisco nivel;

    @ElementCollection
    @CollectionTable(name = "perfil_fatores", joinColumns = @JoinColumn(name = "perfil_id"))
    @Column(name = "fator")
    private List<String> fatores = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "perfil_recomendacoes", joinColumns = @JoinColumn(name = "perfil_id"))
    @Column(name = "recomendacao")
    private List<String> recomendacoes = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private Aluno aluno;
}
