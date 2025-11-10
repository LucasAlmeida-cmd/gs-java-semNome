package com.example.gs_java.repository;

import com.example.gs_java.model.LogDiario;
import com.example.gs_java.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDiarioRepository extends JpaRepository<LogDiario, Long>{
    Page<LogDiario> findAllByUsuario(Usuario usuarioLogado, Pageable pageable);
}
