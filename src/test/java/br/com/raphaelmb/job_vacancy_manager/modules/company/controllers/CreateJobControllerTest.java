package br.com.raphaelmb.job_vacancy_manager.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import br.com.raphaelmb.job_vacancy_manager.modules.company.dto.CreateJobDTO;
import br.com.raphaelmb.job_vacancy_manager.modules.company.entities.CompanyEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.CompanyRepository;
import br.com.raphaelmb.job_vacancy_manager.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
    }
    
    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        var company = CompanyEntity.builder()
            .description("COMPANY_DESCRIPTION")
            .email("email@company.com")
            .password("1234567890")
            .username("COMPANY_USERNAME")
            .name("COMPANY_NAME")
            .build();
        
        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .description("DESCRIPTION_TEST")
            .level("LEVEL_TEST")
            .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(createJobDTO))
            .header("Authorization", TestUtils.generateToken(company.getId(), "job_vacancy"))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_with_nonexistent_company() throws Exception {
        var nonexistentCompanyId = UUID.randomUUID();
        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST") 
                .level("LEVEL_TEST")
                .build();

         mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJSON(createJobDTO))
                .header("Authorization", TestUtils.generateToken(nonexistentCompanyId, "job_vacancy")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
