package br.com.raphaelmb.job_vacancy_manager.modules.candidate.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidateResponseDTO {
    private String access_token;
    private Long expires_in;
    private List<String> roles;
}
