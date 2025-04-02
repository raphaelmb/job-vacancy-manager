package br.com.raphaelmb.job_vacancy_manager.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfileResponseDTO {
    private UUID id;
    private String description;
    private String username;
    private String email;
    private String name;
}
