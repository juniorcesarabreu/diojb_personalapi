package dev.juniorcesarabreu.personapi.service;

import dev.juniorcesarabreu.personapi.dto.request.PersonDTO;
import dev.juniorcesarabreu.personapi.dto.response.MessageResponseDTO;
import dev.juniorcesarabreu.personapi.entity.Person;
import dev.juniorcesarabreu.personapi.mapper.PersonMapper;
import dev.juniorcesarabreu.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
// indica para o spring para gerenciar uma classe que será responsavel pelas regras de negocio da aplicação, tratando melhor suporte transacional
public class PersonService {

    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    // injeção da classe repository
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping // operação http para criar
    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();// lista todo mundo

        // converte uma entidade para dto antes de retornar
        return allPeople.stream()
                .map(personMapper::toDTO) // converte para um DTO
                .collect(Collectors.toList())
                ;
    }
}
