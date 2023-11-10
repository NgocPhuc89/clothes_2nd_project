package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryParentIsNull();

    List<Category> findAllByCategoryParentIsNotNullAndAndCategoryParent_Id(Long id);
}
