package com.example.gs_java.service;

import com.example.gs_java.config.RabbitMQConfig;
import com.example.gs_java.model.LogDiario;
import com.example.gs_java.model.Insight;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.repository.LogDiarioRepository;
import com.example.gs_java.repository.InsightRepository;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsightService {

    @Autowired
    private LogDiarioRepository logDiarioRepository;

    @Autowired
    private InsightRepository insightRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ChatClient chatClient;


    @Autowired
    public InsightService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @Transactional
    public String gerarInsight(Usuario usuarioLogado) {
        List<LogDiario> logs = logDiarioRepository.findByUsuarioAndProcessadoParaInsightFalse(usuarioLogado);

        if (logs.isEmpty()) {
            return "Não há novos dados de bem-estar para analisar. Por favor, registre seus logs diários.";
        }
        String prompt = formatarLogsParaIA(logs, usuarioLogado.getNomeUser());

        String textoInsight = chatClient.prompt()
                .user(prompt)
                .call()
                .content();


        Insight novoInsight = new Insight(textoInsight, LocalDate.now(), usuarioLogado);
        Insight insightSalvo = insightRepository.save(novoInsight);


        for (LogDiario log : logs) {
            log.setProcessadoParaInsight(true);
        }
        logDiarioRepository.saveAll(logs);


        rabbitTemplate.convertAndSend(
                RabbitMQConfig.QUEUE_EMAIL,
                new EmailMessage(usuarioLogado.getEmail(), insightSalvo.getId())
        );


        return textoInsight;
    }


    private String formatarLogsParaIA(List<LogDiario> logs, String nomeUsuario) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Olá! Meu nome é %s. ", nomeUsuario));
        sb.append("Você é um assistente de bem-estar e saúde mental. ");
        sb.append("Analise meus seguintes registros diários de bem-estar:\n\n");

        for (LogDiario log : logs) {
            sb.append(String.format(
                    "Data: %s, Emoção: %s, Horas de Sono: %d, Litros de Água: %.1f, Fez Exercício: %s, Descansou a Mente: %s, Notas: '%s'\n",
                    log.getData(), log.getEmocao(), log.getHorasSono(), log.getAguaLitros(),
                    log.getFezExercicio() ? "Sim" : "Não",
                    log.getDescansouMente() ? "Sim" : "Não",
                    log.getNotas() != null ? log.getNotas() : "N/A"
            ));
        }

        sb.append("\nCom base nesses dados, por favor, me dê um insight curto e acionável. ");
        sb.append("Identifique padrões (ex: 'notei que nos dias que você não bebeu água, sua emoção foi negativa') ");
        sb.append("e sugira 1 ou 2 atividades paliativas para eu fazer (como caminhada, beber mais água, meditação). ");
        sb.append("Seja gentil e encorajador. (Responda em Português do Brasil).");

        return sb.toString();
    }

    public List<Insight> buscarPorUsuario(Usuario usuario) {
        return insightRepository.findByUsuario(usuario);
    }

    public static record EmailMessage(String toEmail, Long insightId) {}
}