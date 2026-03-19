package com.rollback.api_alunos.service;

import com.rollback.api_alunos.model.Aluno;
import com.rollback.api_alunos.model.enums.StatusAluno;
import com.rollback.api_alunos.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + id));
    }

    public List<Aluno> listarPorStatus(StatusAluno status) {
        return alunoRepository.findByStatus(status);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}
