package com.rollback.api_alunos.model;

import com.rollback.api_alunos.model.enums.SituacaoMatricula;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
@NoArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double ira;

    @Column(nullable = false)
    private int semestre;

    @Column(name = "faltas_totais")
    private int faltasTotais;

    @Enumerated(EnumType.STRING)
    private SituacaoMatricula situacao;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToMany
    @JoinTable(
        name = "matricula_disciplinas",
        joinColumns = @JoinColumn(name = "matricula_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();
}
