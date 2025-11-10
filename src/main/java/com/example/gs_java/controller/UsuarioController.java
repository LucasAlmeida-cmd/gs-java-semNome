package com.example.gs_java.controller;

import com.example.gs_java.model.Administrador;
import com.example.gs_java.model.User;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

import java.util.Optional;

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


    @GetMapping("/admin/editar/{id}")
    public String carregarFormularioEdicaoAdmin(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido:" + id));
        model.addAttribute("usuario", usuario);
        return "admin/editar-usuario";
    }

    @PutMapping("/admin/editar/{id}")
    public String salvarEdicao(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.atualizar(id,usuario);
        return "redirect:/admin/dashboard";
    }

    @DeleteMapping("/admin/excluir/{id}")
    public String deletarPorCpf(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.remover(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir usuário: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }



    @GetMapping("/ver-perfil")
    public String verMeuPerfil(@AuthenticationPrincipal User user, Model model) {
        Usuario usuario = usuarioService.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + user.getId()));
        model.addAttribute("usuario", usuario);
        return "usuario/ver-perfil";
    }


    @GetMapping("/editar/{id}")
    public String carregarFormularioEdicaoUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido:" + id));
        model.addAttribute("usuario", usuario);
        return "usuario/editar-perfil";
    }


    @PutMapping("/editar/{id}")
    public String salvarEdicaoUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.atualizar(id,usuario);
        return "redirect:/usuario/dashboard";
    }


























}