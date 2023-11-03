package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
