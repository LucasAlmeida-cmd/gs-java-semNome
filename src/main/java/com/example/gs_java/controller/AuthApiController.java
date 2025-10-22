package com.example.gs_java.controller;

import com.example.gs_java.config.JwtTokenService;
import com.example.gs_java.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class AuthApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

     @Autowired
     private JwtTokenService jwtTokenService;

    record LoginRequest(String email, String senha) {}
    record LoginResponse(String token) {}
    record TokenResponse(String token) {}

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginRequest dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email, dados.senha);
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = jwtTokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }
}