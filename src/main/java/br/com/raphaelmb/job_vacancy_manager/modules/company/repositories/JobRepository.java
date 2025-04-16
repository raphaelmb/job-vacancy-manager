package br.com.raphaelmb.job_vacancy_manager.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>{
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}