package com.example.gs_java.controller;

import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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


    @GetMapping("/editar/{id}")
    public String carregarFormularioEdicao(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido:" + id));
        model.addAttribute("usuario", usuario);
        return "admin/editar-usuario";
    }

    @PutMapping("/editar/{id}")
    public String salvarEdicao(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.atualizar(id,usuario);
        return "redirect:/admin/dashboard";
    }

    @DeleteMapping("/excluir/{id}")
    public String deletarPorCpf(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.remover(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir usuário: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }

}