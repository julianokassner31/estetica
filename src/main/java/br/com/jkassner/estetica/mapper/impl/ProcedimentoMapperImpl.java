package br.com.jkassner.estetica.mapper.impl;

import br.com.jkassner.estetica.dtos.ProcedimentoDto;
import br.com.jkassner.estetica.dtos.ProcedimentoMaterialDto;
import br.com.jkassner.estetica.mapper.ProcedimentoMapper;
import br.com.jkassner.estetica.model.Procedimento;
import br.com.jkassner.estetica.model.ProcedimentoMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class ProcedimentoMapperImpl implements ProcedimentoMapper {

    public static final ProcedimentoMapperImpl INSTANCE = Mappers.getMapper(ProcedimentoMapperImpl.class);

    @Override
    public ProcedimentoDto modelToDto(Procedimento model) {
        ProcedimentoDto procedimentoDto = ProcedimentoMapper.INSTANCE.modelToDto(model);
        if (!model.getProcedimentoMaterialList().isEmpty()) {
            for (ProcedimentoMaterial procedimentoMaterial : model.getProcedimentoMaterialList()) {
                ProcedimentoMaterialDto procedimentoMaterialDto = ProcedimentoMaterialMapperImpl.INSTANCE.modelToDto(procedimentoMaterial);
                procedimentoDto.getProcedimentoMaterialList().add(procedimentoMaterialDto);
            }
        }

        return procedimentoDto;
    }

    @Override
    public Procedimento dtoTomodel(ProcedimentoDto dto) {

        return null;
    }
}
