package br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.exceptions.JobNotFoundException;
import br.com.raphaelmb.job_vacancy_manager.exceptions.UserNotFoundException;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.CandidateRepository;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.entity.ApplyJobEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.repository.ApplyJobRepository;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            });

        this.jobRepository.findById(idJob).orElseThrow(
            () -> {
                throw new JobNotFoundException();
            });
        
        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();
        
        return applyJobRepository.save(applyJob);
    }
}
