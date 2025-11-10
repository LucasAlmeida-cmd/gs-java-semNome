package com.example.gs_java.dtos.security;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String senha;
}
