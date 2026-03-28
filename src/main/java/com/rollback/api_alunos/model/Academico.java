package com.rollback.api_alunos.model;

/**
 * Define o comportamento de entidades com vínculo acadêmico.
 * Implementada por Aluno, que possui histórico de matrículas e semestre de ingresso.
 */
public interface Academico {

    void mostrarHistorico();

    String getSemestreIngresso();
}
