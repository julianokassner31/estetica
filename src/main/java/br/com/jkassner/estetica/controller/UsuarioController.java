package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.dtos.UsuarioDto;
import br.com.jkassner.estetica.dtos.PaginationDto;
import br.com.jkassner.estetica.mapper.UsuarioMapper;
import br.com.jkassner.estetica.model.Usuario;
import br.com.jkassner.estetica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAnyAuthority('CADASTRO', 'ATENDIMENTO', 'LEITURA')")
    @GetMapping(value = "/func")
    public ResponseEntity<PaginationDto<UsuarioDto>> getUsuariosFunc(@RequestParam int pageIndex, @RequestParam int pageSize) {
            PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Usuario> pagination = this.usuarioService.findAll(pageRequest);
        List<UsuarioDto> usuarioDtos = new ArrayList<>(0);
        pagination.getContent().forEach(usuario ->  {
            usuarioDtos.add(UsuarioMapper.INSTANCE.modelToDto(usuario));
        });

        PaginationDto<UsuarioDto> materialDtoPaginationDto = new PaginationDto<>();
        materialDtoPaginationDto.setElements(usuarioDtos);
        materialDtoPaginationDto.setPage(pageIndex);
        materialDtoPaginationDto.setSize(pagination.getSize());
        materialDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        materialDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(materialDtoPaginationDto);
    }

    @PreAuthorize("hasAnyAuthority('CADASTRO', 'ATENDIMENTO', 'LEITURA', 'ADMINISTRADOR')")
    @GetMapping(value = "/cliente")
    public ResponseEntity<PaginationDto<UsuarioDto>> getClientes(@RequestParam int pageIndex, @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Usuario> pagination = this.usuarioService.findAll(pageRequest);
        List<UsuarioDto> usuarioDtos = new ArrayList<>(0);
        pagination.getContent().forEach(usuario ->  {
            usuarioDtos.add(UsuarioMapper.INSTANCE.modelToDto(usuario));
        });

        PaginationDto<UsuarioDto> materialDtoPaginationDto = new PaginationDto<>();
        materialDtoPaginationDto.setElements(usuarioDtos);
        materialDtoPaginationDto.setPage(pageIndex);
        materialDtoPaginationDto.setSize(pagination.getSize());
        materialDtoPaginationDto.setTotalPages(pagination.getTotalPages());
        materialDtoPaginationDto.setTotalElements(pagination.getTotalElements());

        return ResponseEntity.ok(materialDtoPaginationDto);
    }

    @PreAuthorize("hasAnyAuthority('CADASTRO', 'CLIENTE')")
    @PostMapping(value = "/cliente")
    public ResponseEntity<UsuarioDto> createCliente(@RequestBody UsuarioDto req) {
        Usuario usuario = UsuarioMapper.INSTANCE.dtoToModel(req);
        usuario = usuarioService.save(usuario);

        return ResponseEntity.ok(UsuarioMapper.INSTANCE.modelToDto(usuario));
    }

    @PreAuthorize("hasAnyAuthority('CADASTRO')")
    @PostMapping(value = "/func")
    public ResponseEntity<UsuarioDto> createFunc(@RequestBody UsuarioDto req) {
       Usuario usuario = UsuarioMapper.INSTANCE.dtoToModel(req);
        usuario = usuarioService.save(usuario);

        return ResponseEntity.ok(UsuarioMapper.INSTANCE.modelToDto(usuario));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'CADASTRO', 'LEITURA')")
    @GetMapping(value = "/findByNome")
    public ResponseEntity<List<UsuarioDto>> findByName(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioService.findByNomeContainsIgnoreCase(nome);

        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();

        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDtos.add(UsuarioMapper.INSTANCE.modelToDto(usuario));
        }

        return ResponseEntity.ok(usuarioDtos);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'CADASTRO', 'LEITURA')")
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<UsuarioDto> findByName(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(value -> ResponseEntity.ok(UsuarioMapper.INSTANCE.modelToDto(value))).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
