package com.example.gs_java.consumer;

import com.example.gs_java.config.RabbitMQConfig;
import com.example.gs_java.model.Insight;
import com.example.gs_java.repository.InsightRepository;
// Importe o 'EmailMessage' (o record) que você definiu dentro do seu InsightService
import com.example.gs_java.service.InsightService.EmailMessage;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component // <-- Muito importante: Diz ao Spring para gerenciar esta classe
public class EmailConsumer {

    @Autowired
    private JavaMailSender mailSender; // Injeta o serviço de e-mail do Spring

    @Autowired
    private InsightRepository insightRepository; // Para buscar o texto do insight

    /**
     * ✅ ESTE É O "OUVINTE"
     * A anotação @RabbitListener diz ao Spring para "ouvir"
     * a fila que definimos na RabbitMQConfig.
     * * Ele será ativado AUTOMATICAMENTE quando uma mensagem chegar.
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_EMAIL)
    @Transactional
    public void handleEmailMessage(Long insightId) { // <-- 1. Recebe só o ID

        System.out.println("======================================");
        System.out.println("CONSUMIDOR RABBITMQ: Mensagem recebida!");
        System.out.println("Processando insight ID: " + insightId);
        System.out.println("======================================");

        try {
            // 2. Busca o insight COMPLETO no banco
            Insight insight = insightRepository.findById(insightId)
                    .orElseThrow(() -> new EntityNotFoundException("Insight não encontrado: " + insightId));

            // 3. Pega o usuário e o e-mail DELE
            // (Certifique-se que o 'usuario' em Insight não é LAZY ou busque-o se for)
            String emailUsuario = insight.getUsuario().getEmail();

            System.out.println("CONSUMIDOR: Enviando e-mail para: " + emailUsuario);

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailUsuario); // <-- 4. Usa o e-mail do usuário
            email.setSubject("Seu Insight de Bem-Estar Chegou!");
            email.setText(
                    "Olá!\n\n" +
                            "Aqui está o seu insight personalizado:\n\n" +
                            "----------------------------------------\n" +
                            insight.getTexto() + // O texto da IA
                            "\n----------------------------------------\n\n" +
                            "Equipe GS."
            );

            mailSender.send(email);

            System.out.println("CONSUMIDOR: E-mail enviado com sucesso.");

        } catch (Exception e) {
            System.err.println("CONSUMIDOR: Falha ao processar e-mail: " + e.getMessage());
            e.printStackTrace(); // Ajuda a ver o erro
        }
    }
}