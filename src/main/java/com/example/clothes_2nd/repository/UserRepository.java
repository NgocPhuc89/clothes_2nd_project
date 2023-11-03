package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
