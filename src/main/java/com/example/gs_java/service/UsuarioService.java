package com.example.gs_java.service;

import com.example.gs_java.exceptions.UsuarioNotFoundException;
import com.example.gs_java.model.Role;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario adicionar(Usuario usuario){
        usuario.setRole(Role.USUARIO);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos(){return usuarioRepository.findAll();}

    @Transactional
    public void remover(String cpf){
        Usuario usuario = usuarioRepository.findByCpfUser(cpf)
                .orElseThrow(() -> new UsuarioNotFoundException(cpf));
        usuarioRepository.delete(usuario);
    }

    public void atualizar(String cpf, Usuario usuario){
        Usuario usuarioBanco = usuarioRepository.findByCpfUser(cpf)
                .orElseThrow(() -> new UsuarioNotFoundException(cpf));

        usuarioBanco.setRole(Role.USUARIO);
        usuarioBanco.setNomeUser(usuario.getNomeUser());
        usuarioBanco.setEmail(usuario.getEmail());
        usuarioBanco.setPassword(usuario.getPassword());
        usuarioBanco.setCpfUser(usuario.getCpfUser());
        usuarioBanco.setDataAniversario(usuario.getDataAniversario());
        usuarioRepository.save(usuarioBanco);
    }

}
