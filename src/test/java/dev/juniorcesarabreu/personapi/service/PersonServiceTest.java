package dev.juniorcesarabreu.personapi.service;

import dev.juniorcesarabreu.personapi.dto.request.PersonDTO;
import dev.juniorcesarabreu.personapi.dto.response.MessageResponseDTO;
import dev.juniorcesarabreu.personapi.entity.Person;
import dev.juniorcesarabreu.personapi.repository.PersonRepository;
import org.assertj.core.api.AssertionErrorCollector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static dev.juniorcesarabreu.personapi.utils.PersonUtils.createFakeDTO;
import static dev.juniorcesarabreu.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // usa mockito para mockar (gerar um objeto falso)
public class PersonServiceTest {
    // classes de testes devem ser criadas no mesmo nivel de pasta

    @Mock // indica que vai gerar um mock
    private PersonRepository personRepository;

    @InjectMocks // indica que o mock dessa classe vair ser injetado na classe de service
    private PersonService personService;

    @Test
    void TestGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();


        when(personRepository.save(any(Person.class)))
                .thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());

        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
