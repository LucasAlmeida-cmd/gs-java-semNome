package com.example.gs_java.controller;

import com.example.gs_java.dtos.LogDiarioRequestDTO;
import com.example.gs_java.model.LogDiario;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.LogDiarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class LogDiarioController {

    @Autowired
    private LogDiarioService logDiarioService;

    @GetMapping("/novo")
    public String mostrarFormularioLog(Model model) {
        LogDiarioRequestDTO dto = new LogDiarioRequestDTO();
        model.addAttribute("logDiario", dto);
        return "adicionar-log";
    }

    @PostMapping("/salvar")
    public String salvarLog(
            @Valid @ModelAttribute("logDiario") LogDiarioRequestDTO logDTO,
            BindingResult result,
            @AuthenticationPrincipal Usuario usuarioLogado,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "adicionar-log";
        }

        try {
            logDiarioService.adicionarLog(logDTO, usuarioLogado);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Novo log salvo! Você já pode gerar um novo insight.");
            return "redirect:/insights/insightsPorId";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar o log: " + e.getMessage());
            return "redirect:/logs/novo";
        }
    }



    @GetMapping("/logDiarioPorId")
    public String listarLogsUsuario(@AuthenticationPrincipal Usuario usuarioLogado, Model model) {
        var logs = logDiarioService.buscarPorUsuario(usuarioLogado);
        model.addAttribute("logs", logs);
        return "usuario/logDiarioPorId";
    }


    @GetMapping("/editarLog/{id}")
    public String editarLog(@PathVariable Long id, Model model) {
        LogDiario log = logDiarioService.buscarPorId(id);
        model.addAttribute("log", log);
        return "usuario/editarLog";
    }


    @PostMapping("/atualizarLog")
    public String atualizarLog(
            @ModelAttribute LogDiario logDiario,
            @AuthenticationPrincipal Usuario usuarioLogado,
            RedirectAttributes redirectAttributes) {

        try {
            logDiario.setUsuario(usuarioLogado);

            LogDiario existente = logDiarioService.buscarPorId(logDiario.getId());
            if (existente != null) {
                logDiario.setProcessadoParaInsight(existente.getProcessadoParaInsight());
            }

            logDiarioService.salvar(logDiario);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Registro atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar registro: " + e.getMessage());
        }

        return "redirect:/usuario/logDiarioPorId";
    }


    @PostMapping("/excluirLog/{id}")
    public String excluirLog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logDiarioService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Log excluído com sucesso!");
        return "redirect:/usuario/logDiarioPorId";
    }


}
