package com.example.gs_java.controller;

import com.example.gs_java.model.LogDiario;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.LogDiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class LogDiarioController {

    @Autowired
    private LogDiarioService logDiarioService;

    // Página que lista todos os logs do usuário logado
    @GetMapping("/logDiarioPorId")
    public String listarLogsUsuario(@AuthenticationPrincipal Usuario usuarioLogado, Model model) {
        var logs = logDiarioService.buscarPorUsuario(usuarioLogado);
        model.addAttribute("logs", logs);
        return "usuario/logDiarioPorId";
    }

    // Página para editar um log específico
    @GetMapping("/editarLog/{id}")
    public String editarLog(@PathVariable Long id, Model model) {
        LogDiario log = logDiarioService.buscarPorId(id);
        model.addAttribute("log", log);
        return "usuario/editarLog";
    }

    // Atualiza o log (salvar alterações)
    @PostMapping("/atualizarLog")
    public String atualizarLog(
            @ModelAttribute LogDiario logDiario,
            @AuthenticationPrincipal Usuario usuarioLogado,
            RedirectAttributes redirectAttributes) {

        try {
            // Reatribui o usuário logado antes de salvar
            logDiario.setUsuario(usuarioLogado);

            // Busca o log atual no banco para preservar valores que não vieram do formulário
            LogDiario existente = logDiarioService.buscarPorId(logDiario.getId());
            if (existente != null) {
                // Mantém o valor original do campo processadoParaInsight
                logDiario.setProcessadoParaInsight(existente.getProcessadoParaInsight());
            }

            logDiarioService.salvar(logDiario);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Registro atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar registro: " + e.getMessage());
        }

        return "redirect:/usuario/logDiarioPorId";
    }

    // Exclui um log
    @PostMapping("/excluirLog/{id}")
    public String excluirLog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logDiarioService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Log excluído com sucesso!");
        return "redirect:/usuario/logDiarioPorId";
    }


}
