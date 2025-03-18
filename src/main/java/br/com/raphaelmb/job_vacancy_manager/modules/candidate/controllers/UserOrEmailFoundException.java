package br.com.raphaelmb.job_vacancy_manager.modules.candidate.controllers;

public class UserOrEmailFoundException extends RuntimeException { 
    public UserOrEmailFoundException() {
        super("User or email already exists");
    }
}
