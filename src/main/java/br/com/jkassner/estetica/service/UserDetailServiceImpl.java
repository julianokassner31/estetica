package br.com.jkassner.estetica.service;

import br.com.jkassner.estetica.model.Usuario;
import br.com.jkassner.estetica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        List<SimpleGrantedAuthority> roles = usuario.getPermissoes().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPass(), roles);
    }

//    public static void main(String[] args) {
//        String encode = new BCryptPasswordEncoder().encode("123456");
//        System.out.println(encode);
//    }
}
