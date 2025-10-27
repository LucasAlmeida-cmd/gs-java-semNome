package com.example.gs_java.dtos;

import com.example.gs_java.model.Administrador;
import com.example.gs_java.model.Role;
import com.example.gs_java.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserDTO {


    @JsonProperty("id")
    private String codigo;


    @JsonProperty("name")
    private String nomeUser;

    private String email;


    private Role role;


    public UserDTO(User userEntity) {
        this.email = userEntity.getEmail();
        this.role = userEntity.getRole();


        if (userEntity instanceof Administrador) {
            Administrador admin = (Administrador) userEntity;
            this.codigo = admin.getCodigo();
            this.nomeUser = admin.getNomeUser();
        }
    }


    @JsonProperty("role")
    public String getRoleAsString() {
        if (this.role == null) {
            return "user";
        }
        return this.role.name().toLowerCase();
    }



    public String getCodigo() {
        return codigo;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getEmail() {
        return email;
    }

    private Role getRole() {
        return role;
    }
}

