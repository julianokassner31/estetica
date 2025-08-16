package br.com.jkassner.estetica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "valor", nullable = false)
    private double valor;
    @Column(name = "quantidade", nullable = false)
    private double quantidade;
    @ManyToOne(targetEntity = UnidadeMedida.class)
    @JoinColumn(name = "unidade_medida")
    private UnidadeMedida unidadeMedida;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "validade")
    private Date validade;
}
