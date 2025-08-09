package br.com.jkassner.estetica.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialDto {

    private Integer id;
    private String nome;
    private double valor;
    private double quantidade;
    private int unidadeMedida;
    private String descricao;
}
