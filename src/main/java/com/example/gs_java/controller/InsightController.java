package com.example.gs_java.controller;


import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/insights")
public class InsightController {

    @Autowired
    private InsightService insightService;


    @GetMapping
    public String showInsightPage() {
        return "insights/pagina-insights";
    }

    @GetMapping("/insightsPorId")
    public String listarInsightsPorUsuario(
            @AuthenticationPrincipal Usuario usuarioLogado,
            org.springframework.ui.Model model) {
        try {
            var insights = insightService.buscarPorUsuario(usuarioLogado);
            model.addAttribute("insights", insights);
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao carregar insights: " + e.getMessage());
        }

        return "insights/insightsPorId";
    }


    @PostMapping("/gerar")
    public String gerarInsight(
            @AuthenticationPrincipal Usuario usuarioLogado,
            RedirectAttributes redirectAttributes) {

        try {
            String insightTexto = insightService.gerarInsight(usuarioLogado);
            redirectAttributes.addFlashAttribute("insightGerado", insightTexto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Seu insight foi gerado! Um e-mail será enviado em breve.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao gerar insight: " + e.getMessage());
        }
        return "redirect:/insights";
    }
}