package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.dtos.PaginationDto;
import br.com.jkassner.estetica.dtos.UnidadeMedidaDto;
import br.com.jkassner.estetica.mapper.UnidadeMapper;
import br.com.jkassner.estetica.model.UnidadeMedida;
import br.com.jkassner.estetica.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/unidade-medida")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaRepository repository;

    @GetMapping
    public ResponseEntity<PaginationDto<UnidadeMedidaDto>> getUnidadesMedidas(@RequestParam int pageIndex, @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "descricao"));
        Page<UnidadeMedida> pagination = this.repository.findAll(pageRequest);
        List<UnidadeMedidaDto> unidadesDto = new ArrayList<>(0);
        pagination.getContent().forEach(unidadeMedida ->  {
            unidadesDto.add(UnidadeMapper.INSTANCE.modelToDto(unidadeMedida));
        });

        PaginationDto<UnidadeMedidaDto>unidadeMedidaDtoPaginationDto = new PaginationDto<>();
        unidadeMedidaDtoPaginationDto.setElements(unidadesDto);
        unidadeMedidaDtoPaginationDto.setPage(pageIndex);
        unidadeMedidaDtoPaginationDto.setSize(pagination.getSize());
        unidadeMedidaDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        unidadeMedidaDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(unidadeMedidaDtoPaginationDto);
    }
}
