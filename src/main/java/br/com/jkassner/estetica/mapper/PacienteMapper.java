package br.com.jkassner.estetica.mapper;

import br.com.jkassner.estetica.dtos.PacienteDto;
import br.com.jkassner.estetica.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PacienteMapper extends AbstractMapper<Paciente, PacienteDto>{
    PacienteMapper INSTANCE = Mappers.getMapper( PacienteMapper.class );
}
