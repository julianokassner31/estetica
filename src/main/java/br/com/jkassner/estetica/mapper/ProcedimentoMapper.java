package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.ProcedimentoDto;
import br.com.jkassner.estetica.model.Procedimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcedimentoMapper {

    ProcedimentoMapper INSTANCE = Mappers.getMapper(ProcedimentoMapper.class);

    @Mapping(target = "procedimentoMaterialList", ignore = true)
    ProcedimentoDto modelToDto(Procedimento model);
    @Mapping(target = "procedimentoMaterialList", ignore = true)
    Procedimento dtoTomodel(ProcedimentoDto dto);
}
