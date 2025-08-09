package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.dtos.MaterialDto;
import br.com.jkassner.estetica.dtos.PaginationDto;
import br.com.jkassner.estetica.mapper.MaterialMapper;
import br.com.jkassner.estetica.model.Material;
import br.com.jkassner.estetica.repository.MaterialRepository;
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
@RequestMapping(path= "/material")
public class MaterialController {

    @Autowired
    private MaterialRepository repository;

    @GetMapping
    public ResponseEntity<PaginationDto<MaterialDto>> getMateriais(@RequestParam int pageIndex, @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Material> pagination = this.repository.findAll(pageRequest);
        List<MaterialDto> materialDtos = new ArrayList<>(0);
        pagination.getContent().forEach(material ->  {
            materialDtos.add(MaterialMapper.INSTANCE.modelToDto(material));
        });

        PaginationDto<MaterialDto> materialDtoPaginationDto = new PaginationDto<>();
        materialDtoPaginationDto.setElements(materialDtos);
        materialDtoPaginationDto.setPage(pageIndex);
        materialDtoPaginationDto.setSize(pagination.getSize());
        materialDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        materialDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(materialDtoPaginationDto);
    }

    @PostMapping
    public ResponseEntity<MaterialDto> create(@RequestBody MaterialDto req) {
        Material material = MaterialMapper.INSTANCE.dtoToModel(req);
        material = repository.save(material);

        return ResponseEntity.ok(MaterialMapper.INSTANCE.modelToDto(material));
    }

    @GetMapping(value = "/findByNome")
    public ResponseEntity<List<MaterialDto>> findByName(@RequestParam String nome) {
        List<Material> materiais = repository.findByNomeContainsIgnoreCase(nome);

        if (materiais.isEmpty()) return ResponseEntity.noContent().build();

        List<MaterialDto> materialDtos = new ArrayList<>();
        for (Material material : materiais) {
            materialDtos.add(MaterialMapper.INSTANCE.modelToDto(material));
        }

        return ResponseEntity.ok(materialDtos);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<MaterialDto> findByName(@PathVariable Integer id) {
        Optional<Material> material = repository.findById(id);
        return material.map(value -> ResponseEntity.ok(MaterialMapper.INSTANCE.modelToDto(value))).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
