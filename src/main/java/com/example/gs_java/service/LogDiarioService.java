package com.example.gs_java.service;

import com.example.gs_java.dtos.LogDiarioRequestDTO;
import com.example.gs_java.model.LogDiario;
import com.example.gs_java.model.Usuario; // Importe seu modelo de Usuário
import com.example.gs_java.repository.LogDiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Boa prática

@Service
public class LogDiarioService {

    @Autowired
    private LogDiarioRepository logDiarioRepository;


    @Transactional
    public LogDiario adicionarLog(LogDiarioRequestDTO dto, Usuario usuarioLogado) {
        LogDiario novoLog = new LogDiario();
        novoLog.setData(dto.getData());
        novoLog.setEmocao(dto.getEmocao());
        novoLog.setHorasSono(dto.getHorasSono());
        novoLog.setAguaLitros(dto.getAguaLitros());
        novoLog.setFezExercicio(dto.getFezExercicio());
        novoLog.setDescansouMente(dto.getDescansouMente());
        novoLog.setNotas(dto.getNotas());
        novoLog.setUsuario(usuarioLogado);
        return logDiarioRepository.save(novoLog);
    }


    @Transactional
    public LogDiario atualizarLog(Long logId, LogDiarioRequestDTO dto, Usuario usuarioLogado) {

        LogDiario logExistente = logDiarioRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Log não encontrado com ID: " + logId));

        if (!logExistente.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Usuário não autorizado a modificar este log.");
        }

        logExistente.setData(dto.getData());
        logExistente.setEmocao(dto.getEmocao());
        logExistente.setHorasSono(dto.getHorasSono());
        logExistente.setAguaLitros(dto.getAguaLitros());
        logExistente.setFezExercicio(dto.getFezExercicio());
        logExistente.setDescansouMente(dto.getDescansouMente());
        logExistente.setNotas(dto.getNotas());

        return logDiarioRepository.save(logExistente);
    }


    @Transactional
    public void deletarLog(Long logId, Usuario usuarioLogado) {
        LogDiario logExistente = logDiarioRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Log não encontrado com ID: " + logId));

        if (!logExistente.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Usuário não autorizado a deletar este log.");
        }
        logDiarioRepository.delete(logExistente);
    }


    @Transactional(readOnly = true)
    public Page<LogDiario> listarLogsPorUsuario(Usuario usuarioLogado, Pageable pageable) {
        return logDiarioRepository.findAllByUsuario(usuarioLogado, pageable);
    }


    @Transactional(readOnly = true)
    public LogDiario buscarLogPorId(Long logId, Usuario usuarioLogado) {
        LogDiario log = logDiarioRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Log não encontrado com ID: " + logId));

        if (!log.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Usuário não autorizado a visualizar este log.");
        }
        return log;
    }
}