package com.example.gs_java.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String cpf) {
        super("Usuário não encontrado com CPF: " + cpf);
    }
}
