package br.com.jkassner.estetica.mapper.impl;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.mapper.MaterialMapper;
import br.com.jkassner.estetica.model.Material;
import br.com.jkassner.estetica.model.UnidadeMedida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public class MaterialMapperImpl  implements MaterialMapper {

    public static final MaterialMapperImpl INSTANCE = Mappers.getMapper(MaterialMapperImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public MaterialDto modelToDto(Material model) {
        MaterialDto materialDto = MaterialMapper.INSTANCE.modelToDto(model);
        materialDto.setUnidadeMedida(model.getUnidadeMedida().getId());
        materialDto.setVencido(model.getValidade().compareTo(new Date()) <= 0);
        try {
            materialDto.setValidade(sdf.format(model.getValidade()));
        } catch (Exception e){}

        return materialDto;
    }

    @Override
    public Material dtoToModel(MaterialDto materialDto) {
        Material material = MaterialMapper.INSTANCE.dtoToModel(materialDto);
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(materialDto.getUnidadeMedida());
        material.setUnidadeMedida(unidadeMedida);
        try {
            Date validade = sdf.parse(materialDto.getValidade());
            material.setValidade(validade);
        } catch (ParseException e) {}

        return material;
    }
}
