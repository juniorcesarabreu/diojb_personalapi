package dev.juniorcesarabreu.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // codigo de resposta da exception
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(Long id) {
        super("Person not found with ID " + id);
    }
}
