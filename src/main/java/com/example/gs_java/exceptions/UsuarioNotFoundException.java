package com.example.gs_java.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Long id) {
        super("Usuário não encontrado com o ID: " + id);
    }
}
