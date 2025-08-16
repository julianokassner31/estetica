package br.com.jkassner.estetica.mapper.impl;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.dtos.ProcedimentoMaterialDto;
import br.com.jkassner.estetica.mapper.ProcedimentoMaterialMapper;
import br.com.jkassner.estetica.model.Material;
import br.com.jkassner.estetica.model.ProcedimentoMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class ProcedimentoMaterialMapperImpl implements ProcedimentoMaterialMapper {

    public static final ProcedimentoMaterialMapperImpl INSTANCE = Mappers.getMapper(ProcedimentoMaterialMapperImpl.class);

    @Override
    public ProcedimentoMaterialDto modelToDto(ProcedimentoMaterial model) {
        ProcedimentoMaterialDto procedimentoMaterialDto = ProcedimentoMaterialMapper.INSTANCE.modelToDto(model);
        MaterialDto materialDto = MaterialMapperImpl.INSTANCE.modelToDto(model.getMaterial());
        procedimentoMaterialDto.setMaterial(materialDto);

        return procedimentoMaterialDto;
    }

    @Override
    public ProcedimentoMaterial dtoTomodel(ProcedimentoMaterialDto dto) {
        ProcedimentoMaterial procedimentoMaterial = ProcedimentoMaterialMapper.INSTANCE.dtoTomodel(dto);
        Material material = MaterialMapperImpl.INSTANCE.dtoToModel(dto.getMaterial());
        procedimentoMaterial.setMaterial(material);
        return procedimentoMaterial;
    }
}
