package dev.juniorcesarabreu.personapi.mapper;

import dev.juniorcesarabreu.personapi.dto.request.PersonDTO;
import dev.juniorcesarabreu.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper // indica para o mapstruct processar a interface
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    // por convenção do mapStruct o metodo deve se chamar toModel para funcionar a conversão

    // instrui o mapStruct para converter a data, pois o tipo de dado da data no DTO é diferente do tipo de dado na entidade
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
