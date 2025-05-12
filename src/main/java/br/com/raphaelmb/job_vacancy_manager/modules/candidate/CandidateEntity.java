package br.com.raphaelmb.job_vacancy_manager.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    private UUID id;

    @Schema(example = "dev", requiredMode = RequiredMode.REQUIRED, description = "Candidate's name")
    private String name;

    @Pattern(regexp = "\\S+", message = "The field [username] should not have spaces")
    @Schema(example = "javadev", requiredMode = RequiredMode.REQUIRED, description = "Candidate's username")
    private String username;

    @Email(message = "The field [email] should contain a valid email")
    @Schema(example = "dev@example.com")
    private String email;

    @Length(min = 10, max = 100, message = "The field [password] should have a length between 10 and 100 characters")
    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Candidate's password")
    private String password;

    @Schema(example = "Java developer", description = "Candidate's description")
    private String description;

    @Schema(example = "curriculum", description = "Candidate's curriculum")
    private String curriculum;

    @Column(name = "created_at")
    @CreationTimestamp
    @Schema(hidden = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    @Schema(hidden = true)
    private LocalDateTime updatedAt;
}
