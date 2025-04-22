package br.com.raphaelmb.job_vacancy_manager.exceptions;

public class UserNotFoundException extends RuntimeException { 
    public UserNotFoundException() {
        super("User not found");
    }
}
