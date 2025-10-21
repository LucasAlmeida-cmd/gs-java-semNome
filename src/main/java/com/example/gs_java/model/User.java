package com.example.gs_java.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_user_gs_2sem")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario", nullable = false, length = 80)
    private String nomeUser;


    @Column(name = "senha_usuario", nullable = false, length = 100)
    private String password;

    @Column(name = "email_usuario", nullable = false, length = 50, unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;



}
