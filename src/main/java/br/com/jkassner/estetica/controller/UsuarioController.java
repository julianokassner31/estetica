package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.dtos.UsuarioDto;
import br.com.jkassner.estetica.dtos.PaginationDto;
import br.com.jkassner.estetica.mapper.UsuarioMapper;
import br.com.jkassner.estetica.model.Usuario;
import br.com.jkassner.estetica.repository.UsuarioRepository;
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
@RequestMapping(path= "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    /**public ResponseEntity<PaginationDto<UsuarioDto>> getUsuarios(@RequestParam boolean func, @RequestParam int pageIndex, @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Usuario> pagination = this.repository.findByFunc(func, pageRequest);
        List<UsuarioDto> pacientesDto = new ArrayList<>(0);
        pagination.getContent().forEach(paciente ->  {
            pacientesDto.add(UsuarioMapper.INSTANCE.modelToDto(paciente));
        });

        PaginationDto<UsuarioDto> materialDtoPaginationDto = new PaginationDto<>();
        materialDtoPaginationDto.setElements(pacientesDto);
        materialDtoPaginationDto.setPage(pageIndex);
        materialDtoPaginationDto.setSize(pagination.getSize());
        materialDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        materialDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(materialDtoPaginationDto);
    }*/

    @PostMapping(value = "/cliente")
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto req) {
        Usuario usuario = UsuarioMapper.INSTANCE.dtoToModel(req);
        usuario = repository.save(usuario);

        return ResponseEntity.ok(UsuarioMapper.INSTANCE.modelToDto(usuario));
    }

    @PostMapping(value = "/func")
    public ResponseEntity<UsuarioDto> createFunc(@RequestBody UsuarioDto req) {
       Usuario usuario = UsuarioMapper.INSTANCE.dtoToModel(req);
        usuario = repository.save(usuario);

        return ResponseEntity.ok(UsuarioMapper.INSTANCE.modelToDto(usuario));
    }

    @GetMapping(value = "/findByNome")
    public ResponseEntity<List<UsuarioDto>> findByName(@RequestParam String nome) {
        List<Usuario> usuarios = repository.findByNomeContainsIgnoreCase(nome);

        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();

        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDtos.add(UsuarioMapper.INSTANCE.modelToDto(usuario));
        }

        return ResponseEntity.ok(usuarioDtos);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<UsuarioDto> findByName(@PathVariable Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.map(value -> ResponseEntity.ok(UsuarioMapper.INSTANCE.modelToDto(value))).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
