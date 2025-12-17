package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.PermissaoDto;
import br.com.jkassner.estetica.model.Permissao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissaoMapper extends AbstractMapper<Permissao, PermissaoDto> {
    PermissaoMapper INSTANCE = Mappers.getMapper(PermissaoMapper.class);
}
