package com.example.gs_java.consumer;

import com.example.gs_java.config.RabbitMQConfig;
import com.example.gs_java.model.Insight;
import com.example.gs_java.repository.InsightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmailConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private InsightRepository insightRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_EMAIL)
    @Transactional
    public void handleEmailMessage(Long insightId) {

        System.out.println("======================================");
        System.out.println("CONSUMIDOR RABBITMQ: Mensagem recebida!");
        System.out.println("Processando insight ID: " + insightId);
        System.out.println("======================================");

        try {
            Insight insight = insightRepository.findById(insightId)
                    .orElseThrow(() -> new EntityNotFoundException("Insight não encontrado: " + insightId));


            String emailUsuario = insight.getUsuario().getEmail();

            System.out.println("CONSUMIDOR: Enviando e-mail para: " + emailUsuario);

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailUsuario);
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
            e.printStackTrace();
        }
    }
}