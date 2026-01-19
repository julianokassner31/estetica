package br.com.jkassner.estetica.interceptors;

import br.com.jkassner.estetica.utils.SecurityUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class TenantJpaInterceptor {

    @PersistenceContext
    private EntityManager entityManager;

    public void enableTenantFilter() {
        Integer tenantId = SecurityUtils.getIdEmpresa();

        if (tenantId == null) {
            throw new AccessDeniedException("Tenant não encontrado");
        }

        Session session = entityManager.unwrap(Session.class);

        if (session.getEnabledFilter("tenantFilter") == null) {
            session.enableFilter("tenantFilter")
                    .setParameter("idEmpresa", tenantId);
        }
    }
}
