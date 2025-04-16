package br.com.raphaelmb.job_vacancy_manager.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "Java dev")
    private String description;

    @Schema(example = "javadev")
    private String username;

    @Schema(example = "dev@example.com")
    private String email;

    @Schema(example = "dev")
    private String name;
}
