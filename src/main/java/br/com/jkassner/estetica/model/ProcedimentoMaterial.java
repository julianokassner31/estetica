package br.com.jkassner.estetica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class ProcedimentoMaterial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_procedimento")
    private Procedimento procedimento;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_material")
    private Material material;
    @Column(name = "qtd_material")
    private double qtdMaterial;
}
