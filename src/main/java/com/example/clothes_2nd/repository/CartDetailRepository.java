package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
