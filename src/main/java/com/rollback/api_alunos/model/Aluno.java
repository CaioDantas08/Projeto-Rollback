package com.rollback.api_alunos.model;

import com.rollback.api_alunos.model.enums.StatusAluno;
import com.rollback.api_alunos.model.enums.StatusDisciplina;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
@Getter
@Setter
public class Aluno extends Pessoa implements Analisavel, Academico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semestre_ingresso", nullable = false)
    private String semestreIngresso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAluno status;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas = new ArrayList<>();

    public Aluno() {
        super();
    }

    public Aluno(String nome, int idade, String curso) {
        super(nome, idade, curso);
    }

    public String getCurso() {
        return getExtraInfo();
    }

    // Pessoa
    @Override
    public void mostrarDados() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Curso: " + getCurso());
        System.out.println("Status: " + status);
        System.out.println("Semestre de ingresso: " + semestreIngresso);
    }

    // Academico
    @Override
    public void mostrarHistorico() {
        System.out.println("Histórico de matrículas de " + getNome() + ":");
        for (Matricula m : matriculas) {
            System.out.println("  Semestre " + m.getSemestre() + " - " + m.getSituacao());
        }
    }

    // Analisavel
    @Override
    public double calcularRisco() {
        long reprovacoes = matriculas.stream()
                .flatMap(m -> m.getDisciplinas().stream())
                .filter(d -> d.getStatus() == StatusDisciplina.REPROVADO)
                .count();
        return Math.min(reprovacoes * 0.2, 1.0);
    }

    @Override
    public String gerarRecomendacao() {
        double risco = calcularRisco();
        if (risco >= 0.8) return "Acompanhamento urgente recomendado.";
        if (risco >= 0.5) return "Monitorar desempenho nas próximas disciplinas.";
        return "Aluno sem sinais críticos de risco.";
    }
}
