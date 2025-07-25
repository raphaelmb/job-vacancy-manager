package br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.raphaelmb.job_vacancy_manager.exceptions.JobNotFoundException;
import br.com.raphaelmb.job_vacancy_manager.exceptions.UserNotFoundException;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.entity.ApplyJobEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.entity.CandidateEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.repository.ApplyJobRepository;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.repository.CandidateRepository;
import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.JobEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply for job with candidate not found")
    public void should_not_be_able_to_apply_to_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertInstanceOf(UserNotFoundException.class, e);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for job with job not found")
    public void should_not_be_able_to_apply_to_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(candidate.getId(), null);
        } catch (Exception e) {
            assertInstanceOf(JobNotFoundException.class, e);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();
    
        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();
    
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));

        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
