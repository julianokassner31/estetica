package br.com.jkassner.estetica.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UsuarioDto {

    private Integer id;
    private String nome;
    private String cpf;
    private Date nascimento;
    private String celular;
    private String telefone;
    private String email;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String pass;
    private List<PermissaoDto> permissoes;
}
