package br.com.jkassner.estetica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Procedimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "valor", nullable = false)
    private double valor;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "procedimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedimentoMaterial> procedimentoMaterialList = new ArrayList<>(0);
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idempresa", nullable = false)
    private Empresa empresa;
}
