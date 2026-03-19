package com.example.gs_java.config.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    // Cria um "balde" com 5 créditos, que recupera 1 crédito a cada minuto
    private final Bucket bucket = Bucket4j.builder()
            .addLimit(Bandwidth.classic(5, Refill.intervally(1, Duration.ofMinutes(1))))
            .build();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Aplica apenas para métodos que gravam no banco (POST/PUT)
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (bucket.tryConsume(1)) {
                return true; // Deixa passar
            } else {
                // Se acabar os créditos, retorna erro 429 (Too Many Requests)
                response.setStatus(429);
                response.getWriter().write("Limite de requisicoes excedido. Tente novamente em 1 minuto.");
                return false;
            }
        }
        return true;
    }
}