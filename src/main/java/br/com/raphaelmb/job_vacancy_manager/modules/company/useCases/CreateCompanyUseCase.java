package br.com.raphaelmb.job_vacancy_manager.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.exceptions.UserOrEmailFoundException;
import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.CompanyEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
       this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent((company) -> {
        throw new UserOrEmailFoundException();
       });
       var password = passwordEncoder.encode(companyEntity.getPassword());
       companyEntity.setPassword(password);
       return this.companyRepository.save(companyEntity);
    }
}
