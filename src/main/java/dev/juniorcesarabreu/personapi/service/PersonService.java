package dev.juniorcesarabreu.personapi.service;

import dev.juniorcesarabreu.personapi.dto.request.PersonDTO;
import dev.juniorcesarabreu.personapi.dto.response.MessageResponseDTO;
import dev.juniorcesarabreu.personapi.entity.Person;
import dev.juniorcesarabreu.personapi.exception.PersonNotFoundException;
import dev.juniorcesarabreu.personapi.mapper.PersonMapper;
import dev.juniorcesarabreu.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
// indica para o spring para gerenciar uma classe que será responsavel pelas regras de negocio da aplicação, tratando melhor suporte transacional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    // injeção da classe repository
    private PersonRepository personRepository;

//    @Autowired
//    public PersonService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();// lista todo mundo

        // converte uma entidade para dto antes de retornar
        return allPeople.stream()
                .map(personMapper::toDTO) // converte para um DTO
                .collect(Collectors.toList())
                ;
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
//        // Optional evita verificações como nulas
//        Optional<Person> optionalPerson = personRepository.findById(id);
//
//        // Exception para informar ao usuario que o id não existe no sistema
//        if (optionalPerson.isEmpty()) {
//            throw new PersonNotFoundException(id);
//        }
//
//        return personMapper.toDTO(optionalPerson.get());

        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatePerson = personRepository.save(personToUpdate);

        return createMessageResponse(updatePerson.getId(), "Updated person with ID ");
    }

    // é boa prática (clean code) colocar os metodos publicos primeiro e os privados por ultimo
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
