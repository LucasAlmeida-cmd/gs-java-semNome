package com.example.gs_java.dtos;

public class AuthResponseDTO {

    private String token;
    private UserDTO user;

    public AuthResponseDTO(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public UserDTO getUser() { return user; }
}
