package dev.juniorcesarabreu.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true) // constraint: unique, não permite duplicados
    private String cpf;

    private LocalDate birthDate;

    // relacionamento entre tabela Person e Phone
    // FetchType.LAZY - perfomance
    // cascade - extrategia de inserção
    // CascadeType.PERSIST - persiste em cascata
    // CascadeType.MERGE - merge em cascata
    // CascadeType.REMOVE - remove em cascata
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Phone> phones;
}
