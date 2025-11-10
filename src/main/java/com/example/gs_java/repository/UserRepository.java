package com.example.gs_java.repository;

import com.example.gs_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findAll();
    Optional<Object> findByNomeUser(String username);
}
