package br.com.raphaelmb.job_vacancy_manager.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.raphaelmb.job_vacancy_manager.modules.candidate.CandidateEntity;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.raphaelmb.job_vacancy_manager.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
   @Autowired
   private CreateCandidateUseCase createCandidateUseCase;

   @Autowired
   private ProfileCandidateUseCase profileCandidateUseCase;

   @PostMapping("/")
   public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
      try {
         var result = this.createCandidateUseCase.execute(candidateEntity);
         return ResponseEntity.status(201).body(result);
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   } 

   @GetMapping("/")
   @PreAuthorize("hasRole('CANDIDATE')")
   public ResponseEntity<Object> get(HttpServletRequest request) {
      var candidateId = request.getAttribute("candidate_id");
      try {
         var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
         return ResponseEntity.ok().body(profile);
      } catch (Exception e) {
         // TODO: change this
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }
}
