package br.com.jkassner.estetica.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedimentoMaterialDto {

    private Integer id;
    private MaterialDto material;
    private double qtdMaterial;
}
