package br.com.raphaelmb.job_vacancy_manager.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class CreateJobDTO {
    @Schema(example = "Java developer", requiredMode = RequiredMode.REQUIRED)
    private String descripton;    

    @Schema(example = "Gym pass", requiredMode = RequiredMode.REQUIRED)
    private String benefits;    

    @Schema(example = "Junior", requiredMode = RequiredMode.REQUIRED)
    private String level;    
}
