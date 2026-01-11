package br.com.jkassner.estetica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cnpj", nullable = false, length = 11)
    private String cnpj;
    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;
    @Column(name = "responsavel", nullable = false)
    private String responsavel;
}
