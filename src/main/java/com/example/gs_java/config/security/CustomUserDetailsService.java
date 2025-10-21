package com.example.gs_java.config.security;

import com.example.gs_java.model.Usuario;
import com.example.gs_java.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Tentando logar com email: " + username);

        Usuario usuario = usuarioRepository.findByEmail(username.trim().toLowerCase())
                .orElseThrow(() -> {
                    System.out.println("❌ Usuário não encontrado: " + username);
                    return new UsernameNotFoundException("Usuário não encontrado: " + username);
                });

        System.out.println("✅ Usuário encontrado: " + usuario.getEmail());

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();
    }
}