package br.com.jkassner.estetica.model;

import br.com.jkassner.estetica.model.auditor.AuditorInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FilterDef(
        name = "tenantFilter",
        parameters = @ParamDef(name = "idEmpresa", type = Integer.class)
)
@Filter(name = "tenantFilter",  condition = "idempresa = :idEmpresa")
@Entity
@Table(name="usuario")
@Getter
@Setter
public class Usuario  extends AuditorInfo implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "nascimento", nullable = false)
    private Date nascimento;
    @Column(name = "celular", nullable = false)
    private String celular;
    private String telefone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "rua", nullable = false)
    private String rua;
    @Column(name = "numero", nullable = false)
    private String numero;
    @Column(name = "bairro", nullable = false)
    private String bairro;
    @Column(name = "cidade", nullable = false)
    private String cidade;
    @Column(name = "pass")
    private String pass;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "usuario_permissao",
            joinColumns = {@JoinColumn(name = "id_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_permissao") })
    private List<Permissao> permissoes = new ArrayList<>(0);
}
