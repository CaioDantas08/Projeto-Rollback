package com.rollback.api_alunos.exception;

public class AlunoNaoEncontradoException extends Exception {

    public AlunoNaoEncontradoException(Long id) {
        super("Aluno não encontrado com ID: " + id);
    }
}
