package dev.juniorcesarabreu.personapi.repository;

import dev.juniorcesarabreu.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> { // tipo do Objeto, tipo da id
    // extendendo esta interface com springframework não é preciso abrir e fechar conexões com bd


}
