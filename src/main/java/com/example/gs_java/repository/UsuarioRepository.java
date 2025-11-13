package com.example.gs_java.repository;

import com.example.gs_java.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpfUser(String cpfUser);
    void deleteByCpfUser(String cpfUser);

    Optional<Usuario> findByEmail(String lowerCase);
}
