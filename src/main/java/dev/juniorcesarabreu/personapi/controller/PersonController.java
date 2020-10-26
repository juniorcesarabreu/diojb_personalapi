package dev.juniorcesarabreu.personapi.controller;

import dev.juniorcesarabreu.personapi.dto.response.MessageResponseDTO;
import dev.juniorcesarabreu.personapi.entity.Person;
import dev.juniorcesarabreu.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController// controlador associado a uma API REST
@RequestMapping("/api/v1/people")// caminho de acesso principal da API (nivel 1)
public class PersonController {
    // o controller é so um ponto de entrada, o ideal que as regras de negocio não estejam aqui, pois dificulta a rotina de testes unitarios e manutenção

    // injeção de dependencia da classe PersonService
    private PersonService personService;

    @Autowired
    // indica para o spring injetar uma implementação de repository dentro do construtor. adicionar no construtor pode facilitar ao fazer testes unitarios
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // instrui para retornar o status de criado
    public MessageResponseDTO createPerson(@RequestBody Person person) { //@RequestBody indica que o objeto será passado na requisição
        return personService.createPerson(person);
    }
}
