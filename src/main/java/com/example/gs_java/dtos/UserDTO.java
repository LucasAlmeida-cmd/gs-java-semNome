package com.example.gs_java.dtos;

import com.example.gs_java.model.Administrador;
import com.example.gs_java.model.Role;
import com.example.gs_java.model.User;
import com.example.gs_java.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String nomeUser;

    private String email;
    private String cpfUser;
    private LocalDate dataAniversario;
    private Role role;

    public UserDTO(User userEntity) {
        this.id = userEntity.getId();
        this.nomeUser = userEntity.getNomeUser();
        this.email = userEntity.getEmail();
        this.role = userEntity.getRole();

        if (userEntity instanceof Administrador admin) {
            this.id = admin.getId();
            this.nomeUser = admin.getNomeUser();
        }

        else if (userEntity instanceof Usuario usuario) {
            this.id = usuario.getId();
            this.nomeUser = usuario.getNomeUser();
            this.cpfUser = usuario.getCpfUser();
            this.dataAniversario = usuario.getDataAniversario();
        }
    }

    @JsonProperty("role")
    public String getRoleAsString() {
        if (this.role == null) {
            return "user";
        }
        return this.role.name().toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getEmail() {
        return email;
    }

    public String getCpfUser() {
        return cpfUser;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    private Role getRole() {
        return role;
    }
}
