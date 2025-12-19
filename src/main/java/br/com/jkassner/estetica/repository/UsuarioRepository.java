package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends AbstractRepository<Usuario>, JpaRepository<Usuario, Integer> {

    @EntityGraph(attributePaths = "permissoes")
    Optional<Usuario> findByUsername(String username);
    Page<Usuario> findByFunc(boolean func, Pageable page);
}
