package com.example.gs_java.repository;

import com.example.gs_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<Object> findByNomeUser(String username);
}
