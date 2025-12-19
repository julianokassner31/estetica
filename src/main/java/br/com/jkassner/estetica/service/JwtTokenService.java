package br.com.jkassner.estetica.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenService {

    public static final String SECRET = "53696044405558904234195156492744";

    public String gerarToken(Authentication user) {
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(user.getName())
                    .issuer("estetica-auth-server")
                    .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .claim("authorities", user.getAuthorities())
                    .build();

            JWSSigner signer = new MACSigner(SECRET);
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
