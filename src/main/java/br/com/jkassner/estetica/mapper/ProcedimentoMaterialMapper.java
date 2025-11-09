package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.ProcedimentoMaterialDto;
import br.com.jkassner.estetica.model.ProcedimentoMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcedimentoMaterialMapper {

    ProcedimentoMaterialMapper INSTANCE = Mappers.getMapper(ProcedimentoMaterialMapper.class);

    @Mapping(target = "material", ignore = true)
    ProcedimentoMaterialDto modelToDto(ProcedimentoMaterial model);
    @Mapping(target = "material", ignore = true)
    ProcedimentoMaterial dtoTomodel(ProcedimentoMaterialDto dto);
}
