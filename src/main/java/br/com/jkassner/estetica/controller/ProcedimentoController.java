package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.dtos.PaginationDto;
import br.com.jkassner.estetica.dtos.ProcedimentoDto;
import br.com.jkassner.estetica.mapper.MaterialMapper;
import br.com.jkassner.estetica.mapper.ProcedimentoMapper;
import br.com.jkassner.estetica.mapper.impl.ProcedimentoMapperImpl;
import br.com.jkassner.estetica.model.Material;
import br.com.jkassner.estetica.model.Procedimento;
import br.com.jkassner.estetica.repository.ProcedimentoRepository;
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
@RequestMapping(path= "/procedimento")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoRepository repository;

    @GetMapping
    public ResponseEntity<PaginationDto<ProcedimentoDto>> getProcedimentos(@RequestParam int pageIndex, @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Procedimento> pagination = this.repository.findAll(pageRequest);
        List<ProcedimentoDto> procedimentosDto = new ArrayList<>(0);
        pagination.getContent().forEach(procedimento ->  {
            procedimentosDto.add(ProcedimentoMapperImpl.INSTANCE.modelToDto(procedimento));
        });

        PaginationDto<ProcedimentoDto> procedimentoDtoPaginationDto = new PaginationDto<>();
        procedimentoDtoPaginationDto.setElements(procedimentosDto);
        procedimentoDtoPaginationDto.setPage(pageIndex);
        procedimentoDtoPaginationDto.setSize(pagination.getSize());
        procedimentoDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        procedimentoDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(procedimentoDtoPaginationDto);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ProcedimentoDto> findById(@PathVariable int id) {
        Optional<Procedimento> procedimento = repository.findById(id);
        return procedimento.map(value -> ResponseEntity.ok(ProcedimentoMapperImpl.INSTANCE.modelToDto(value))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/findByNome")
    public ResponseEntity<List<ProcedimentoDto>> findByNome(@RequestParam String nome) {

        List<Procedimento> procedimentos = repository.findByNome(nome);
        if (procedimentos.isEmpty()) return ResponseEntity.notFound().build();

        List<ProcedimentoDto> listDto = new ArrayList<>(0);

        for (Procedimento procedimento : procedimentos) {
            ProcedimentoDto procedimentoDto = ProcedimentoMapperImpl.INSTANCE.modelToDto(procedimento);
            listDto.add(procedimentoDto);
        }

        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<ProcedimentoDto> create(@RequestBody ProcedimentoDto req) {
        Procedimento procedimento = ProcedimentoMapper.INSTANCE.dtoTomodel(req);
        procedimento = repository.save(procedimento);

        return ResponseEntity.ok(ProcedimentoMapper.INSTANCE.modelToDto(procedimento));
    }
}
