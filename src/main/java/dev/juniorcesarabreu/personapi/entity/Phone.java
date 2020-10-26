package dev.juniorcesarabreu.personapi.entity;

import dev.juniorcesarabreu.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // jpa - indica que é uma entidade
@Data // lombok - getters e setter
@Builder // lombok - para criar objetos utilizando uma estrutura de builder
@AllArgsConstructor // lombok - insere construtores com todos os argumentos
@NoArgsConstructor // lombok - insere construtores sem argumentos
public class Phone {

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // estrategia de geração de id
    private Long id;

    @Enumerated(EnumType.STRING) // enumeração
    @Column(nullable = false) // constraint: obrigatorio (not null)
    private PhoneType type;

    @Column(nullable = false)
    private String number;


}
