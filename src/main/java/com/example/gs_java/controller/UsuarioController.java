package com.example.gs_java.controller;

import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/novo")
    public String mostrarFormCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }


    @PostMapping("/salvar")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario) {
        usuarioService.adicionar(usuario);
        return "redirect:/login";
    }
}