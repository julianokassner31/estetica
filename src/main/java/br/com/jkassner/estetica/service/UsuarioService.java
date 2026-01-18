package br.com.jkassner.estetica.service;

import br.com.jkassner.estetica.interceptors.TenantJpaInterceptor;
import br.com.jkassner.estetica.model.Usuario;
import br.com.jkassner.estetica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private TenantJpaInterceptor tenantJpaInterceptor;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(PageRequest page) {
        tenantJpaInterceptor.enableTenantFilter();
        return usuarioRepository.findAll(page);
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findByNomeContainsIgnoreCase(String nome) {
        return usuarioRepository.findByNomeContainsIgnoreCase(nome);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
