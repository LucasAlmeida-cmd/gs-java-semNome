package com.example.gs_java.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService; // O Spring injetará seu CustomUserDetailsService aqui

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Obter o token do cabeçalho
        var authorizationHeader = request.getHeader("Authorization");

        // 2. Validar o token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring(7); // Remove o "Bearer "
            var subjectEmail = jwtTokenService.getSubject(token); // Valida e pega o email

            // 3. Se o token for válido e o usuário ainda não estiver autenticado
            if (subjectEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 4. Carregar o usuário do banco de dados
                UserDetails userDetails = userDetailsService.loadUserByUsername(subjectEmail);

                // 5. Criar o "Authentication" e colocar no contexto do Spring
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // Não precisamos de credenciais (senha)
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 6. Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}