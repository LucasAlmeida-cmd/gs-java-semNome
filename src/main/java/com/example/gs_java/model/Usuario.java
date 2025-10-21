package com.example.gs_java.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user_usuario_gs_2sem")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("USUARIO")
@PrimaryKeyJoinColumn(name = "id_usuario", referencedColumnName = "id")
public class Usuario extends User{


    @Column(name = "cpf_usuario", nullable = false, length = 14, unique = true)
    private String cpfUser;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_aniversario_usuario", nullable = false)
    private LocalDate dataAniversario;

    public Usuario(Long id, String nomeUser, LocalDate dataAniversario, String password, String email, Role role, String cpfUser) {
        super(id, nomeUser, password, email, role);
        this.cpfUser = VerificaCPF.validarEFormatar(cpfUser);
        this.dataAniversario = dataAniversario;
    }

    public void setCpfUser(String cpfUser) {
        this.cpfUser = VerificaCPF.validarEFormatar(cpfUser);
    }
}
