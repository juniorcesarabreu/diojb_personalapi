package dev.juniorcesarabreu.personapi.controller;

import dev.juniorcesarabreu.personapi.dto.request.PersonDTO;
import dev.juniorcesarabreu.personapi.dto.response.MessageResponseDTO;
import dev.juniorcesarabreu.personapi.exception.PersonNotFoundException;
import dev.juniorcesarabreu.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController// controlador associado a uma API REST
@RequestMapping("/api/v1/people")// caminho de acesso principal da API (nivel 1)
@AllArgsConstructor(onConstructor = @__(@Autowired)) // lombok: permite remover a implementação do construtor (que está comentado), pois ele será gerado pelo lombok em tempo de compilação
public class PersonController {
    // o controller é so um ponto de entrada, o ideal que as regras de negocio não estejam aqui, pois dificulta a rotina de testes unitarios e manutenção

    // injeção de dependencia da classe PersonService
    private PersonService personService;

//    @Autowired
//    // indica para o spring injetar uma implementação de repository dentro do construtor. adicionar no construtor pode facilitar ao fazer testes unitarios
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // instrui para retornar o status de criado
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        // @RequestBody indica que o objeto será passado na requisição
        // @Valid indica para executar as validações
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}") // mapeia para evitar conflito com listAll()
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException { // @PathVariable indica que está passando o id
        return personService.findById(id);
    }

    // PUT permite apenas atualização completa, para fazer uma atualização parcial, é necessário utilizar o método PATCH
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
