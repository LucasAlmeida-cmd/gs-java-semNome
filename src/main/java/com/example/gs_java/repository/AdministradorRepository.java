package com.example.gs_java.repository;

import com.example.gs_java.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByCodigo(String codigo);
}
