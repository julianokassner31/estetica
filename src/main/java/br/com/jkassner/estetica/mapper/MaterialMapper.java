package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper( MaterialMapper.class );

    @Mapping(target = "unidadeMedida", ignore = true)
    @Mapping(target = "vencido", ignore = true)
    @Mapping(target = "validade", expression = "java(material.getValidade() != null ? new java.text.SimpleDateFormat(\"yyyy-MM-dd\").format(material.getValidade()) : null)")
    MaterialDto modelToDto(Material material);
    @Mapping(target = "unidadeMedida", ignore = true)
    @Mapping(target = "validade", ignore = true)
    Material dtoToModel(MaterialDto materialDto);
}
