package br.com.jkassner.estetica.filter;

import br.com.jkassner.estetica.custom.UserDetailsCustom;
import br.com.jkassner.estetica.service.JwtTokenService;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.matches("(?i)^Bearer\\s+.+$")) {
            String token = header.replaceFirst("(?i)^Bearer\\s+", "");

            try {
                SignedJWT signedJWT = SignedJWT.parse(token);

                JWSVerifier verifier = new MACVerifier(tokenService.getSecret());

                if (!signedJWT.verify(verifier)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
                if (expiration == null || expiration.before(new Date())) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                String username = signedJWT.getJWTClaimsSet().getSubject();

                List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());
                List<Map<String, String>> roles = (List<Map<String, String>>) signedJWT.getJWTClaimsSet().getClaim("authorities");

                for (Map<String, String> role : roles) {
                    role.values().forEach(roleName -> authorities.add(new SimpleGrantedAuthority(roleName)));
                }

                Integer idEmpresa = null;
                if(!request.getRequestURI().contains("/usuario/cliente")) {
                    idEmpresa = (Integer) signedJWT.getJWTClaimsSet().getClaim("empresa");
                }

                UserDetailsCustom userDetailsCustom = new UserDetailsCustom(username, null, authorities, idEmpresa);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetailsCustom,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                e.printStackTrace();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
