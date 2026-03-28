package com.rollback.api_alunos.model;

/**
 * Define o comportamento de entidades que podem ter o risco acadêmico analisado.
 * Implementada por Aluno, pois é o foco de desistência do projeto.
 */
public interface Analisavel {

    double calcularRisco();

    String gerarRecomendacao();
}
