package com.example.gs_java.dtos;

import com.example.gs_java.model.LogDiario;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LogDiarioResponseDTO {
    private Long id;
    private LocalDate data;
    private String emocao;
    private Integer horasSono;
    private Double aguaLitros;
    private Boolean fezExercicio;
    private Boolean descansouMente;
    private String notas;
    private Long usuarioId;


    public LogDiarioResponseDTO(LogDiario log) {
        this.id = log.getId();
        this.data = log.getData();
        this.emocao = log.getEmocao();
        this.horasSono = log.getHorasSono();
        this.aguaLitros = log.getAguaLitros();
        this.fezExercicio = log.getFezExercicio();
        this.descansouMente = log.getDescansouMente();
        this.notas = log.getNotas();
        this.usuarioId = log.getUsuario().getId();
    }
}