package br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.exceptions.UserOrEmailFoundException;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.entity.CandidateEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.repository.CandidateRepository;


@Service
public class CreateCandidateUseCase {
   @Autowired
   private CandidateRepository candidateRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   public CandidateEntity execute(CandidateEntity candidateEntity) {
      this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((user) -> {
        throw new UserOrEmailFoundException();
      });

      var password = passwordEncoder.encode(candidateEntity.getPassword());
      candidateEntity.setPassword(password);

      return this.candidateRepository.save(candidateEntity);
    }
}
