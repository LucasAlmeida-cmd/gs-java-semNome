package com.example.gs_java.exceptions;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String id) {
        super("Usuário não encontrado com o ID: " + id);
    }
}
