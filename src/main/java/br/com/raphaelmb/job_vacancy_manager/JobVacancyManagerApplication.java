package br.com.raphaelmb.job_vacancy_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Job Vacancy Manager",
		description = "API for managing job vacancies",
		version = "1"
	)
)
public class JobVacancyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobVacancyManagerApplication.class, args);
	}

}