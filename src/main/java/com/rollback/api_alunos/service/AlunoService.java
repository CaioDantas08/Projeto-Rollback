package com.rollback.api_alunos.service;

import com.rollback.api_alunos.exception.AlunoNaoEncontradoException;
import com.rollback.api_alunos.exception.DadosInvalidosException;
import com.rollback.api_alunos.model.Aluno;
import com.rollback.api_alunos.model.enums.StatusAluno;
import com.rollback.api_alunos.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ExecutorService riskAnalysisExecutor;

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
 
    // retorna alunos com risco >= 0.5 usando ExecutorService e lambdas
    @Transactional(readOnly = true)
    public List<Aluno> listarAlunosEmRisco() {
        List<Aluno> alunos = alunoRepository.findAll();
        alunos.forEach(this::inicializarHistoricoAcademico);

        List<Callable<AlunoRiscoResultado>> tarefas = alunos.stream()
                .map(aluno -> (Callable<AlunoRiscoResultado>) () ->
                        new AlunoRiscoResultado(aluno, aluno.calcularRisco()))
                .collect(Collectors.toList());

        return executarAnalise(tarefas).stream()
                .map(this::obterResultado)
                .filter(resultado -> resultado.risco() >= 0.5)
                .map(AlunoRiscoResultado::aluno)
                .collect(Collectors.toList());
    }

    private void inicializarHistoricoAcademico(Aluno aluno) {
        aluno.getMatriculas().forEach(matricula -> matricula.getDisciplinas().size());
    }

    private List<Future<AlunoRiscoResultado>> executarAnalise(List<Callable<AlunoRiscoResultado>> tarefas) {
        try {
            return riskAnalysisExecutor.invokeAll(tarefas);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Analise de risco interrompida.", e);
        }
    }

    private AlunoRiscoResultado obterResultado(Future<AlunoRiscoResultado> tarefa) {
        try {
            return tarefa.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Analise de risco interrompida.", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Falha ao calcular risco do aluno.", e);
        }
    }

    private record AlunoRiscoResultado(Aluno aluno, double risco) {
    }
}

