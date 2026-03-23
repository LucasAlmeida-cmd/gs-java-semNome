package com.example.gs_java.repository;

import com.example.gs_java.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpfUser(String cpfUser);
    void deleteByCpfUser(String cpfUser);

    Optional<Usuario> findByEmail(String lowerCase);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_insight WHERE usuario_id IN (SELECT id FROM tb_usuario WHERE data_ultimo_login < CURRENT_DATE - INTERVAL '7 days')", nativeQuery = true)
    void deletarInsightsInativos();


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_usuario WHERE data_ultimo_login < CURRENT_DATE - INTERVAL '7 days' OR data_ultimo_login IS NULL", nativeQuery = true)
    void deletarUsuariosInativos();

}
