package com.rollback.api_alunos.model;

import com.rollback.api_alunos.model.enums.StatusDisciplina;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disciplinas")
@Getter
@Setter
@NoArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "periodo_ideal", nullable = false)
    private int periodoIdeal;

    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;

    private String professor;

    @Enumerated(EnumType.STRING)
    private StatusDisciplina status;

    @ElementCollection
    @CollectionTable(name = "disciplina_notas", joinColumns = @JoinColumn(name = "disciplina_id"))
    @Column(name = "nota")
    private List<Double> notas = new ArrayList<>();

    private double media;

    private int faltas;

    public double calcularMedia() {
        if (notas.isEmpty()) return 0.0;
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
