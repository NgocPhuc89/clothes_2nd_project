package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
