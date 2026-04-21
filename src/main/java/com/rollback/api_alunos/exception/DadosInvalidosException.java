package com.rollback.api_alunos.exception;

public class DadosInvalidosException extends RuntimeException {

    public DadosInvalidosException(String campo) {
        super("Dado obrigatório ausente ou inválido: " + campo);
    }
}
