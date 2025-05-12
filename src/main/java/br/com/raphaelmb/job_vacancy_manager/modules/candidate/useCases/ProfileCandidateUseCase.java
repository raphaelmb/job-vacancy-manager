package br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.exceptions.UserNotFoundException;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.CandidateRepository;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.dto.CandidateProfileResponseDTO;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateProfileResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId).orElseThrow(() -> {
                throw new UserNotFoundException();
            });
        
        var canidateDTO = CandidateProfileResponseDTO.builder()
            .id(candidate.getId())
            .description(candidate.getDescription())
            .username(candidate.getUsername())
            .email(candidate.getEmail())
            .name(candidate.getName())
            .build();

        return canidateDTO;
    }
    
}
