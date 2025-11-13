package com.example.gs_java.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestAiController {

    private final ChatClient chatClient;


    @Autowired
    public TestAiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    @GetMapping("/test-ai")
    public Map<String, String> testarGpt() {
        String resposta = chatClient.prompt()
                .user("Qual a capital do Brasil?")
                .call()
                .content();

        return Map.of("pergunta", "Qual a capital do Brasil?", "resposta", resposta);
    }
}