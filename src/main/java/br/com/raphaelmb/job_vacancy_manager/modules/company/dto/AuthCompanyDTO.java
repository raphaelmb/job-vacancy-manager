package br.com.raphaelmb.job_vacancy_manager.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
    private String password;    
    private String username;    
}
