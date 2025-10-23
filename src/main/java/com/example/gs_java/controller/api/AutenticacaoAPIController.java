package com.example.gs_java.controller.api;


import com.example.gs_java.config.security.JwtTokenService;
import com.example.gs_java.dtos.LoginRequestDTO;
import com.example.gs_java.dtos.TokenResponseDTO;
import com.example.gs_java.model.User;
import com.example.gs_java.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AutenticacaoAPIController {

    @Autowired
    @Lazy
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenService tokenService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginRequestDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }
}
