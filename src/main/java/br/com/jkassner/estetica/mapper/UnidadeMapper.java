package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.UnidadeMedidaDto;
import br.com.jkassner.estetica.model.UnidadeMedida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnidadeMapper extends AbstractMapper<UnidadeMedida, UnidadeMedidaDto>{
    UnidadeMapper INSTANCE = Mappers.getMapper( UnidadeMapper.class );
}
