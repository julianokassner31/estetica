package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer> {

    @Query("select p from Procedimento p where p.nome like %?1% order by p.nome")
    public List<Procedimento> findByNome(String query);
}
