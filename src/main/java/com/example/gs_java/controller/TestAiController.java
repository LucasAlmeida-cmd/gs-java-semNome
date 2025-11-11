package com.example.gs_java.controller; // Pode ser em qualquer pacote de controller

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class TestAiController {

    private final ChatClient chatClient;

    // Injeta o ChatClient (que o Spring AI configura automaticamente)
    @Autowired
    public TestAiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * Um endpoint de teste simples para a IA.
     */
    @GetMapping("/test-ai")
    public Map<String, String> testarGpt() {
        // 1. Faz uma pergunta simples
        String resposta = chatClient.prompt()
                .user("Qual a capital do Brasil?")
                .call()
                .content(); // Pega a resposta

        // 2. Retorna a resposta como JSON
        return Map.of("pergunta", "Qual a capital do Brasil?", "resposta", resposta);
    }
}