package com.example.gs_java.repository;

import com.example.gs_java.model.Insight;
import com.example.gs_java.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsightRepository extends JpaRepository<Insight, Long> {

    List<Insight> findByUsuario(Usuario usuario);
}