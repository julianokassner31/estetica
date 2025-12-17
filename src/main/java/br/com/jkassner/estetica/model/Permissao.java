package br.com.jkassner.estetica.model;

import br.com.jkassner.estetica.enums.EnumPermissoes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "permissao")
@Getter
@Setter
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "nome")
    private EnumPermissoes nome;
    @Column(name = "descricao")
    private String descricao;
}
