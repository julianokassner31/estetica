package br.com.jkassner.estetica.custom;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsCustom implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    @Getter
    private Integer idEmpresa;

    public UserDetailsCustom(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer idEmpresa) {
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
