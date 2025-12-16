package br.com.jkassner.estetica.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private boolean func;
}
