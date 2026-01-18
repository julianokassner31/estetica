package br.com.jkassner.estetica.interceptors;

import br.com.jkassner.estetica.utils.SecurityUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class TenantJpaInterceptor {

    @PersistenceContext
    private EntityManager entityManager;

    public void enableTenantFilter() {
        Session session = entityManager.unwrap(Session.class);

        if (session.getEnabledFilter("tenantFilter") == null) {
            session.enableFilter("tenantFilter")
                    .setParameter("idEmpresa", SecurityUtils.getIdEmpresa());
        }
    }
}
