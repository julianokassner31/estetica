package br.com.jkassner.estetica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "unidade_medida")
@Getter
@Setter
public class UnidadeMedida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "abreviacao", nullable = false)
    private String abreviacao;
}
