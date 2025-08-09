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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "procedimento_material", joinColumns = { @JoinColumn(name = "id_procedimento") },
            inverseJoinColumns = {@JoinColumn(name = "id_material") })
    private List<Material> materiais = new ArrayList<>(0);

}
