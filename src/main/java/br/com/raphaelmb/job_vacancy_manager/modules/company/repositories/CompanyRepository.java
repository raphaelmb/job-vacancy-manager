package br.com.raphaelmb.job_vacancy_manager.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<CompanyEntity> findByUsername(String username);
} 
