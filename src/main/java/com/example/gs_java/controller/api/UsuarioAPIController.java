package com.example.gs_java.controller.api;

import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioAPIController {


    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.ok(usuarioService.adicionar(usuario));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        usuarioService.atualizar(id, usuario);
        return ResponseEntity.noContent().build();
    }


}