package br.com.raphaelmb.job_vacancy_manager.modules.candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.raphaelmb.job_vacancy_manager.modules.candidate.entity.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<CandidateEntity> findByUsername(String username);
}
