package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
