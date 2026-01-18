package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends AbstractRepository<Usuario>, JpaRepository<Usuario, Integer> {

    @EntityGraph(attributePaths = "permissoes")
    @Query("select u from Usuario u where u.email = ?1 and u.enabled")
    Optional<Usuario> findByUsername(String email);
}