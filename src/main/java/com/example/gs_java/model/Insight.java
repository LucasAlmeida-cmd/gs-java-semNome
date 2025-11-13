package com.example.gs_java.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "TB_INSIGHT_gs")
@Data
@NoArgsConstructor
public class Insight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private LocalDate dataGeracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Insight(String texto, LocalDate dataGeracao, Usuario usuario) {
        this.texto = texto;
        this.dataGeracao = dataGeracao;
        this.usuario = usuario;
    }
}