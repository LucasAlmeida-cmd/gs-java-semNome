package com.example.gs_java.controller;

import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ContentController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model, Principal principal) {
        String admin = principal.getName();
        model.addAttribute("nomeAdmin", admin);
        return "dashbord-admin";
    }

    @GetMapping("/usuario/dashboard")
    public String dashboardUsuario(Principal principal, Model model) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorEmail(principal.getName());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario", usuario);
            return "dashbord-usuario";
        } else {
            return "redirect:/login?error=userNotFound";
        }
    }



}
