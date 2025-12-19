package br.com.jkassner.estetica.controller;

import br.com.jkassner.estetica.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService service;

    @PostMapping(value="/token")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(body.get("username"), body.get("password"));
        Authentication auth =
                this.authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = service.gerarToken(auth);
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", token);

        return ResponseEntity.ok(map);
    }
}
