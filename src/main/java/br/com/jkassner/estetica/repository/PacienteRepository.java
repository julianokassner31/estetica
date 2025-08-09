package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends AbstractRepository<Paciente>, JpaRepository<Paciente, Integer> {}
