package dev.juniorcesarabreu.personapi.service;

import dev.juniorcesarabreu.personapi.dto.response.MessageResponseDTO;
import dev.juniorcesarabreu.personapi.entity.Person;
import dev.juniorcesarabreu.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service // indica para o spring para gerenciar uma classe que será responsavel pelas regras de negocio da aplicação, tratando melhor suporte transacional
public class PersonService {

    // injeção da classe repository
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping // operação http para criar
    public MessageResponseDTO createPerson(Person person) {

        Person savedPerson = personRepository.save(person);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
