package br.com.jkassner.estetica.model.auditor;

import br.com.jkassner.estetica.custom.UserDetailsCustom;
import br.com.jkassner.estetica.utils.SecurityUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditorInfo {

    @Column(name = "idempresa", updatable = false)
    private Integer idEmpresa;
    @CreatedBy
    @Column(name = "createdby", updatable = false)
    private Integer createdBy;
    @CreatedDate
    @Column(name = "createdat", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedBy
    @Column(name = "updatedby")
    private Integer updatedBy;
    @LastModifiedDate
    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        UserDetailsCustom user = SecurityUtils.getUser();
        this.idEmpresa = user.getIdEmpresa();
        this.createdBy = user.getIdUsuario();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        UserDetailsCustom user = SecurityUtils.getUser();
        this.updatedBy = user.getIdUsuario();
        this.updatedAt = LocalDateTime.now();
    }

}
