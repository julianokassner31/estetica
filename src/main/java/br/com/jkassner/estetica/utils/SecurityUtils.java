package br.com.jkassner.estetica.utils;

import br.com.jkassner.estetica.custom.UserDetailsCustom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserDetailsCustom getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof UserDetailsCustom user)) {
            throw new IllegalStateException("Usuário não autenticado");
        }

        return user;
    }
}
