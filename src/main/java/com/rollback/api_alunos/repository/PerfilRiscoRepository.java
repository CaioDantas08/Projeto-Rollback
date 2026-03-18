package com.rollback.api_alunos.repository;

import com.rollback.api_alunos.model.PerfilRisco;
import com.rollback.api_alunos.model.enums.NivelRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRiscoRepository extends JpaRepository<PerfilRisco, Long> {

    Optional<PerfilRisco> findByAlunoId(Long alunoId);

    List<PerfilRisco> findByNivel(NivelRisco nivel);
}
