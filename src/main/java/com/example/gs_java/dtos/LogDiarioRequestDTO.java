package com.example.gs_java.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LogDiarioRequestDTO {


    @NotNull(message = "A data não pode ser nula")
    private LocalDate data;

    @NotBlank(message = "O campo emoção é obrigatório")
    private String emocao;

    @NotNull(message = "Horas de sono é obrigatório")
    @Min(value = 0, message = "Valor mínimo de sono é 0")
    @Max(value = 24, message = "Valor máximo de sono é 24")
    private Integer horasSono;

    @NotNull(message = "Litros de água é obrigatório")
    @PositiveOrZero(message = "A quantidade de água não pode ser negativa")
    private Double aguaLitros;

    @NotNull(message = "O campo 'fez exercício' é obrigatório")
    private Boolean fezExercicio;

    @NotNull(message = "O campo 'descansou a mente' é obrigatório")
    private Boolean descansouMente;

    private String notas;



}