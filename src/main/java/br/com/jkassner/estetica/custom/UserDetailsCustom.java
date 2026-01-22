package br.com.jkassner.estetica.custom;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsCustom implements UserDetails {
    @Getter
    private String nome;
    @Getter
    private Integer idEmpresa;
    @Getter Integer idUsuario;
    private final String username;
    private final String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsCustom(Integer idUsuario, String nome, String username, String password, Collection<? extends GrantedAuthority> authorities, Integer idEmpresa) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.idEmpresa = idEmpresa;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
