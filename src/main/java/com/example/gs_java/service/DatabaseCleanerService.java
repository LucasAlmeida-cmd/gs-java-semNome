package com.example.gs_java.service;



import com.example.gs_java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseCleanerService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Roda todos os dias às 03:00 da manhã
    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional
    public void executarLimpezaGeral() {
        try {
            System.out.println("--- INICIANDO FAXINA NO BANCO ZENLOG ---");


            usuarioRepository.deletarInsightsInativos();
            System.out.println("Insights antigos removidos.");


            usuarioRepository.deletarUsuariosInativos();
            System.out.println("Usuários inativos removidos.");

            System.out.println("--- BANCO DE DADOS OTIMIZADO COM SUCESSO ---");
        } catch (Exception e) {
            System.err.println("Erro na limpeza automática: " + e.getMessage());
        }
    }
}