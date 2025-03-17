package br.com.raphaelmb.job_vacancy_manager.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "The field [username] should not have spaces")
    private String username;

    @Email(message = "The field [email] should contain a valid email")
    private String email;

    @Length(min = 10, max = 100, message = "The field [password] should have a length between 10 and 100 characters")
    private String password;
    private String description;
    private String curriculum;
}
