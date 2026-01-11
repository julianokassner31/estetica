package br.com.jkassner.estetica.service;

import br.com.jkassner.estetica.custom.UserDetailsCustom;
import br.com.jkassner.estetica.model.Usuario;
import br.com.jkassner.estetica.repository.UsuarioRepository;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenService {

    @Getter
    @Value("${token.secret-key}")
    private String secret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoService cryptoService;

    public String gerarToken(Authentication user) {

        try {

            UserDetailsCustom userDetailsCustom = (UserDetailsCustom) user.getPrincipal();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(userDetailsCustom.getUsername())
                    .issuer("estetica-auth-server")
                    .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .claim("authorities", userDetailsCustom.getAuthorities())
                    .claim("cliente", cryptoService.encrypt(String.valueOf(userDetailsCustom.getIdEmpresa())))
                    .build();

            JWSSigner signer = new MACSigner(secret);
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claims
            );

            signedJWT.sign(signer);

            return signedJWT.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }
}
