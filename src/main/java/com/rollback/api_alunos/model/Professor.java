package com.rollback.api_alunos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professores")
@Getter
@Setter
public class Professor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Professor() {
        super();
    }

    public Professor(String nome, int idade, String departamento) {
        super(nome, idade, departamento);
    }

    public String getDepartamento() {
        return getExtraInfo();
    }

    @Override
    public void mostrarDados() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Departamento: " + getDepartamento());
    }
}
