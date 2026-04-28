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
        // cada entrada agora mapeia o nome do campo para uma lambda que verifica se é inválido
        Map<String, Supplier<Boolean>> validacoes = new LinkedHashMap<>();
        validacoes.put("nome",             () -> aluno.getNome() == null || aluno.getNome().isBlank());
        validacoes.put("semestreIngresso", () -> aluno.getSemestreIngresso() == null || aluno.getSemestreIngresso().isBlank());
        validacoes.put("status",           () -> aluno.getStatus() == null);
 
        // lambda no forEach: percorre cada validação e lança exceção se inválida
        validacoes.forEach((campo, invalido) -> {
            if (invalido.get()) throw new DadosInvalidosException(campo);
        });
 
        return alunoRepository.save(aluno);
    }
 
    public void deletar(Long id) throws AlunoNaoEncontradoException {
        buscarPorId(id); // garante que o aluno existe antes de deletar
        alunoRepository.deleteById(id);
    }
 
    // retorna alunos com risco >= 0.5 usando lambda no filter
    public List<Aluno> listarAlunosEmRisco() {
        return alunoRepository.findAll().stream()
                .filter(aluno -> aluno.calcularRisco() >= 0.5)  // lambda aqui
                .collect(Collectors.toList());
    }
}

