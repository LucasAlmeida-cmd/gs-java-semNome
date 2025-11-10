package com.example.gs_java.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "TB_LOG_DIARIO_gs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String emocao;

    @Column(nullable = false)
    private Integer horasSono;

    @Column(nullable = false)
    private Double aguaLitros;

    @Column(nullable = false)
    private Boolean fezExercicio;

    @Column(nullable = false)
    private Boolean descansouMente;

    private String notas;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}