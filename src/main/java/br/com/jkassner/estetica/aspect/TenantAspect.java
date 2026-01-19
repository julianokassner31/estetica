package br.com.jkassner.estetica.aspect;

import br.com.jkassner.estetica.interceptors.TenantJpaInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantAspect {

    private final TenantJpaInterceptor tenantJpaInterceptor;

    public TenantAspect(TenantJpaInterceptor tenantJpaInterceptor) {
        this.tenantJpaInterceptor = tenantJpaInterceptor;
    }

    @Around("@annotation(br.com.jkassner.estetica.annotation.TenantAware)")
    public Object applyTenant(ProceedingJoinPoint joinPoint) throws Throwable {
        tenantJpaInterceptor.enableTenantFilter();
        return joinPoint.proceed();
    }
}
