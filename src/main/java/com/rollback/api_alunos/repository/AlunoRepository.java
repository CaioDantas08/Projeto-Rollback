package com.rollback.api_alunos.repository;

import com.rollback.api_alunos.model.Aluno;
import com.rollback.api_alunos.model.enums.StatusAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByStatus(StatusAluno status);
}
