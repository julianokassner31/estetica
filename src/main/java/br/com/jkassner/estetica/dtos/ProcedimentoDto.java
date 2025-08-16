package br.com.jkassner.estetica.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class   ProcedimentoDto {
    private Integer id;
    private String nome;
    private double valor;
    private String descricao;
    private List<ProcedimentoMaterialDto> procedimentoMaterialList = new ArrayList<>(0);
}
