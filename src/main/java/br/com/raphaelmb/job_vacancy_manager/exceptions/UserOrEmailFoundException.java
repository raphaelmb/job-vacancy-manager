package br.com.raphaelmb.job_vacancy_manager.exceptions;

public class UserOrEmailFoundException extends RuntimeException { 
    public UserOrEmailFoundException() {
        super("User or email already exists");
    }
}
