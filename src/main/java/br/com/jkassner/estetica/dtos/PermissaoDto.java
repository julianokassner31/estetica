package br.com.jkassner.estetica.dtos;

import br.com.jkassner.estetica.enums.EnumPermissoes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDto {
    private int id;
    private EnumPermissoes nome;
    private String descricao;
}
