package com.example.gs_java.config.security;

import com.example.gs_java.model.User;
import com.example.gs_java.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Tentando logar com email: " + username);

        User user = userRepository.findByEmail(username.trim().toLowerCase())
                .orElseThrow(() -> {
                    System.out.println("❌ Usuário não encontrado: " + username);
                    return new UsernameNotFoundException("Usuário não encontrado: " + username);
                });

        System.out.println("✅ Usuário encontrado: " + user.getEmail());

        return user;
    }
}