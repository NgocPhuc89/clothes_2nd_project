package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
