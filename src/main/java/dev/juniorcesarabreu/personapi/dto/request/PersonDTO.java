package dev.juniorcesarabreu.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    // DTO - data transfer object
    // utilizado para fazer transferencia de dados
    // permite fazer a validação dos dados de entrada na camada de controller

    private Long id;

    @NotEmpty // Não permite vazio
    @Size(min = 2, max = 100) // limita o tamanho minimo e maximo
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty
    @CPF // valida CPF
    private String cpf;

    private String birthDate;

    @Valid // valida todos os dados do telefone
    @NotEmpty
    private List<PhoneDTO> phones;
}
