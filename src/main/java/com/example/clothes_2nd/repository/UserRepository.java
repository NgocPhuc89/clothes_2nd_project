package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
