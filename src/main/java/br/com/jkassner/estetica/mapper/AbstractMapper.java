package br.com.jkassner.estetica.mapper;

public interface AbstractMapper<M, D> {

    D modelToDto(M model);
    M dtoToModel(D dto);

}
