package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    @Query(value = "SELECT u FROM User as u where u.username is not null")
    Page<User> countUsers(Pageable pageable);
}
