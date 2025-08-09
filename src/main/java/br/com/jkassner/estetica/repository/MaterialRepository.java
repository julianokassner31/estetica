package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    public List<Material> findByNomeContainsIgnoreCase(String nome);
}
