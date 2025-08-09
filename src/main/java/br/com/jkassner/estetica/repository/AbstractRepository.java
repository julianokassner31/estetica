package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Paciente;

import java.util.List;

public interface AbstractRepository<T>{

    public List<Paciente> findByNomeContainsIgnoreCase(String nome);
}
