package com.example.gs_java.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.gs_java.model.User;
import com.example.gs_java.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String gerarToken(User usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("API gs_java")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secretKey);
            return JWT.require(algoritmo)
                    .withIssuer("API gs_java")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}