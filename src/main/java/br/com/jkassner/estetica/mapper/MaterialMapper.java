package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper( MaterialMapper.class );

    @Mapping(source = "unidadeMedida.id", target = "unidadeMedida")
    MaterialDto modelToDto(Material material);
    @Mapping(source = "unidadeMedida", target = "unidadeMedida.id")
    Material dtoToModel(MaterialDto materialDto);
}
