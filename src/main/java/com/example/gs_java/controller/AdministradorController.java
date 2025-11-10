package com.example.gs_java.controller;

import com.example.gs_java.model.Administrador;
import com.example.gs_java.model.User;
import com.example.gs_java.service.AdministradorService;
import com.example.gs_java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministradorController {


    @Autowired
    AdministradorService administradorService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model) {
        model.addAttribute("usuarios",usuarioService.findAll());
        return "admin/listar-usuarios";
    }

    @GetMapping("/meuPerfil")
    public String verMeuPerfil(@AuthenticationPrincipal User user, Model model) {
        Administrador admin = administradorService.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuário admin não encontrado: " + user.getId()));
        model.addAttribute("admin", admin);
        return "admin/ver-perfil";
    }




}
