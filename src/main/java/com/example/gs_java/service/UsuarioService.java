package com.example.gs_java.service;

import com.example.gs_java.exceptions.UsuarioNotFoundException;
import com.example.gs_java.model.Role;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario adicionar(Usuario usuario){
        usuario.setRole(Role.USUARIO);
        usuario.setEmail(usuario.getEmail().trim().toLowerCase());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos(){return usuarioRepository.findAll();}

    @Transactional
    public void remover(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        usuarioRepository.delete(usuario);
    }

    public void atualizar(Long id, Usuario usuario){
        Usuario usuarioBanco = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        usuarioBanco.setRole(Role.USUARIO);
        usuarioBanco.setNomeUser(usuario.getNomeUser());
        usuarioBanco.setEmail(usuario.getEmail().trim().toLowerCase());
        usuarioBanco.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioBanco.setCpfUser(usuarioBanco.getCpfUser());
        usuarioBanco.setDataAniversario(usuario.getDataAniversario());
        usuarioRepository.save(usuarioBanco);
    }

    public Optional<Usuario> buscarPorEmail(String name) {
        return usuarioRepository.findByEmail(name);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
