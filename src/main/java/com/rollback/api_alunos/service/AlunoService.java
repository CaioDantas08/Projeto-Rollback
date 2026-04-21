package com.rollback.api_alunos.service;

import com.rollback.api_alunos.exception.AlunoNaoEncontradoException;
import com.rollback.api_alunos.exception.DadosInvalidosException;
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

    // throws declara que este método pode propagar uma exceção verificada
    public Aluno buscarPorId(Long id) throws AlunoNaoEncontradoException {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));
    }

    public List<Aluno> listarPorStatus(StatusAluno status) {
        return alunoRepository.findByStatus(status);
    }

    public Aluno salvar(Aluno aluno) {
        // throw manual ao detectar condição inválida nos dados de entrada
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new DadosInvalidosException("nome");
        }
        if (aluno.getSemestreIngresso() == null || aluno.getSemestreIngresso().isBlank()) {
            throw new DadosInvalidosException("semestreIngresso");
        }
        if (aluno.getStatus() == null) {
            throw new DadosInvalidosException("status");
        }

        return alunoRepository.save(aluno);
    }

    public void deletar(Long id) throws AlunoNaoEncontradoException {
        buscarPorId(id); // garante que o aluno existe antes de deletar
        alunoRepository.deleteById(id);
    }
}
