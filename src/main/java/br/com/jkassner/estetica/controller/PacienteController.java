package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.dtos.PacienteDto;
import br.com.jkassner.estetica.dtos.PaginationDto;
import br.com.jkassner.estetica.mapper.PacienteMapper;
import br.com.jkassner.estetica.model.Paciente;
import br.com.jkassner.estetica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public ResponseEntity<PaginationDto<PacienteDto>> getPacientes(@RequestParam int pageIndex, @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Paciente> pagination = this.repository.findAll(pageRequest);
        List<PacienteDto> pacientesDto = new ArrayList<>(0);
        pagination.getContent().forEach(paciente ->  {
            pacientesDto.add(PacienteMapper.INSTANCE.modelToDto(paciente));
        });

        PaginationDto<PacienteDto> materialDtoPaginationDto = new PaginationDto<>();
        materialDtoPaginationDto.setElements(pacientesDto);
        materialDtoPaginationDto.setPage(pageIndex);
        materialDtoPaginationDto.setSize(pagination.getSize());
        materialDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        materialDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(materialDtoPaginationDto);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> create(@RequestBody PacienteDto req) {
        Paciente paciente = PacienteMapper.INSTANCE.dtoToModel(req);
        paciente = repository.save(paciente);

        return ResponseEntity.ok(PacienteMapper.INSTANCE.modelToDto(paciente));
    }

    @GetMapping(value = "/findByNome")
    public ResponseEntity<List<PacienteDto>> findByName(@RequestParam String nome) {
        List<Paciente> pacientes = repository.findByNomeContainsIgnoreCase(nome);

        if (pacientes.isEmpty()) return ResponseEntity.noContent().build();

        List<PacienteDto> pacienteDtos = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            pacienteDtos.add(PacienteMapper.INSTANCE.modelToDto(paciente));
        }

        return ResponseEntity.ok(pacienteDtos);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<PacienteDto> findByName(@PathVariable Integer id) {
        Optional<Paciente> paciente = repository.findById(id);
        return paciente.map(value -> ResponseEntity.ok(PacienteMapper.INSTANCE.modelToDto(value))).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
