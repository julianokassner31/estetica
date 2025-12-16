package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.UsuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper extends AbstractMapper<br.com.jkassner.estetica.model.Usuario, UsuarioDto>{
    UsuarioMapper INSTANCE = Mappers.getMapper( UsuarioMapper.class );
}
