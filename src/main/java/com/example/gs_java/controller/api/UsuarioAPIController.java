package com.example.gs_java.controller.api;

import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioAPIController {


    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.ok(usuarioService.adicionar(usuario));
    }


}