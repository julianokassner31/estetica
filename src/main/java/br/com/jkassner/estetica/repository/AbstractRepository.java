package br.com.jkassner.estetica.repository;

import br.com.jkassner.estetica.model.Usuario;

import java.util.List;

public interface AbstractRepository<T>{

    public List<Usuario> findByNomeContainsIgnoreCase(String nome);
}
