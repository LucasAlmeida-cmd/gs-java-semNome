package com.example.gs_java.service;

import com.example.gs_java.exceptions.UsuarioNotFoundException;
import com.example.gs_java.model.Administrador;
import com.example.gs_java.model.Role;
import com.example.gs_java.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrador adicionar(Administrador administrador){
        administrador.setRole(Role.ADMIN);
        administrador.setPassword(passwordEncoder.encode(administrador.getPassword()));
        return administradorRepository.save(administrador);
    }

    public List<Administrador> listarTodos(){
        return administradorRepository.findAll();
    }

    public void removerAdmin(Long id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        administradorRepository.deleteById(id);
    }

    public void atualizarAdminPorCpf(String codigo, Administrador administradorAtualizado) {
        Administrador administrador = administradorRepository.findByCodigo(codigo);
        if (administrador == null) {
            throw new UsuarioNotFoundException(codigo);
        }
        administrador.setNomeUser(administradorAtualizado.getNomeUser());
        administrador.setDataAniversario(administradorAtualizado.getDataAniversario());
        administrador.setEmail(administradorAtualizado.getEmail());
        administrador.setPassword(passwordEncoder.encode(administrador.getPassword()));
        administradorRepository.save(administrador);
    }
}
