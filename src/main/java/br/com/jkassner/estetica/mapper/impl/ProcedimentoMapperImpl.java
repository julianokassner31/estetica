package br.com.jkassner.estetica.mapper.impl;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.dtos.ProcedimentoDto;
import br.com.jkassner.estetica.mapper.MaterialMapper;
import br.com.jkassner.estetica.mapper.ProcedimentoMapper;
import br.com.jkassner.estetica.model.Material;
import br.com.jkassner.estetica.model.Procedimento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class ProcedimentoMapperImpl implements ProcedimentoMapper {

    public static final ProcedimentoMapperImpl INSTANCE = Mappers.getMapper(ProcedimentoMapperImpl.class);

    @Override
    public ProcedimentoDto modelToDto(Procedimento model) {
        ProcedimentoDto procedimentoDto = ProcedimentoMapper.INSTANCE.modelToDto(model);
        if (!model.getMateriais().isEmpty()) {
            for (Material material : model.getMateriais()) {
                MaterialDto materialDto = MaterialMapper.INSTANCE.modelToDto(material);
                procedimentoDto.getMateriais().add(materialDto);
            }
        }

        return procedimentoDto;
    }

    @Override
    public Procedimento dtoTomodel(ProcedimentoDto dto) {

        return null;
    }
}
