package br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.JobEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {
    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
