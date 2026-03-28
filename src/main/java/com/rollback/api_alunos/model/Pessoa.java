package com.rollback.api_alunos.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 * É abstrata pois nunca instanciamos uma "Pessoa" genérica —
 * sempre será um Aluno ou Professor.
 *
 * @MappedSuperclass faz o JPA herdar os campos (nome, idade) nas tabelas filhas
 */
@MappedSuperclass
public abstract class Pessoa {

    @Column(nullable = false)
    private String nome;

    private int idade;

    private String info;

    public Pessoa() {}

    public Pessoa(String nome, int idade, String info) {
        this.nome = nome;
        this.idade = idade;
        this.info = info;
    }

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setExtraInfo(String info) { this.info = info; }

    public String getNome() { return this.nome; }
    public int getIdade() { return this.idade; }
    public String getExtraInfo() { return this.info; }

    public abstract void mostrarDados();
}
