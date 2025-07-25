package br.com.raphaelmb.job_vacancy_manager.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.raphaelmb.job_vacancy_manager.modules.company.dto.AuthCompanyDTO;
import br.com.raphaelmb.job_vacancy_manager.modules.company.dto.AuthCompanyResponseDTO;
import br.com.raphaelmb.job_vacancy_manager.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
       var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
        () -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) 
            throw new AuthenticationException();

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var roles = Arrays.asList("COMPANY");

        Algorithm algorithm  = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("job_vacancy")
            .withSubject(company.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", roles)
            .sign(algorithm);
        
        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .roles(roles)
            .build();

        return authCompanyResponseDTO;
    }
}
