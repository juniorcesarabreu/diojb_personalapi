package dev.juniorcesarabreu.personapi.dto.request;

import dev.juniorcesarabreu.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private Long id;

    @Enumerated(EnumType.STRING) // enumeração
    private PhoneType type;

    @NotEmpty // não pode ser vazio
    @Size(min = 13, max = 14) // limite minimo e maximo do campo
    private String number;
}
