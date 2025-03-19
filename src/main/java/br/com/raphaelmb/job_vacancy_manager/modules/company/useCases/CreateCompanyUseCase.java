package br.com.raphaelmb.job_vacancy_manager.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.raphaelmb.job_vacancy_manager.modules.candidate.controllers.UserOrEmailFoundException;
import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.CompanyEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
       this. companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent((company) -> {
        throw new UserOrEmailFoundException();
       });
       return this.companyRepository.save(companyEntity);
    }
}
