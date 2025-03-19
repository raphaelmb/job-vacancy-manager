package br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.modules.candidate.CandidateEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.CandidateRepository;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.controllers.UserOrEmailFoundException;


@Service
public class CreateCandidateUseCase {
   @Autowired
   private CandidateRepository candidateRepository;

   public CandidateEntity execute(CandidateEntity candidateEntity) {
      this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((user) -> {
        throw new UserOrEmailFoundException();
      });
      return this.candidateRepository.save(candidateEntity);
    }
}
