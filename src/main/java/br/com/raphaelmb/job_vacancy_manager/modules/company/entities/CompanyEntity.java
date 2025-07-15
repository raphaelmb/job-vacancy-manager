package br.com.raphaelmb.job_vacancy_manager.modules.company.entities;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    private UUID id;

    @Schema(example = "company", requiredMode = RequiredMode.REQUIRED, description = "Company's name")
    private String name;
    
    @Schema(example = "description", description = "Company's description")
    private String description;

    @Schema(example = "website.com", description = "Company's website")
    private String website;

    @Pattern(regexp = "\\S+", message = "The field [username] should not have spaces")
    @Schema(example = "company", requiredMode = RequiredMode.REQUIRED, description = "Company's username")
    private String username;

    @Email(message = "The field [email] should contain a valid email")
    @Schema(example = "company@company.com", requiredMode = RequiredMode.REQUIRED, description = "Company's email")
    private String email;

    @Length(min = 10, max = 100, message = "The field [password] should have a length between 10 and 100 characters")
    @Schema(example = "company@1234", requiredMode = RequiredMode.REQUIRED, description = "Company's password")
    private String password;

    @Column(name = "created_at")
    @Schema(hidden = true)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(hidden = true)
    @CreationTimestamp
    private LocalDateTime updatedAt;
}
