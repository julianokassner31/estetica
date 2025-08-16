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
    private Integer unidadeMedida;
    private String descricao;
    private String validade;
    private boolean vencido;
}
