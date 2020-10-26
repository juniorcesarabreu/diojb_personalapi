package dev.juniorcesarabreu.personapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// controlador associado a uma API REST
@RequestMapping("/api/v1/people")// caminho de acesso principal da API (nivel 1)
public class PersonController {

  @GetMapping// operação http do tipo get
  public String getBook() {
    return "API Test";
  }
}
