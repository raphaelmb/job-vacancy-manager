package br.com.raphaelmb.job_vacancy_manager.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.raphaelmb.job_vacancy_manager.modules.company.dto.CreateJobDTO;
import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.JobEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Job Vacancy", description = "Job vacancy info")
    @Operation(summary = "Job register", description = "Register jobs in a company")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = JobEntity.class))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
            .benefits(createJobDTO.getBenefits())
            .description(createJobDTO.getDescripton())
            .level(createJobDTO.getLevel())
            .companyId(UUID.fromString(companyId.toString()))
            .build();

        var result = this.createJobUseCase.execute(jobEntity);
        return ResponseEntity.status(201).body(result);
    }
}
