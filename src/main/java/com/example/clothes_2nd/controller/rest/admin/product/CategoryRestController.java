package com.example.clothes_2nd.controller.rest.admin.product;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.repository.CategoryRepository;
import com.example.clothes_2nd.service.admin.category.CategoryAdminService;
import com.example.clothes_2nd.service.admin.category.response.CategoryAdminListResponse;
import com.example.clothes_2nd.service.home.categoryHome.response.CategoryListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryRestController {

    private final CategoryAdminService categoryAdminService;

    @GetMapping
    public List<CategoryAdminListResponse> getAllCategories() {
        return categoryAdminService.getAllCategories();
    }

    @GetMapping("/{id}/subCategories")
    public List<CategoryAdminListResponse> getSubcategories(@PathVariable Long id){
        return categoryAdminService.getSubcategories(id);
    }

    @GetMapping("/{id}/nestedCategories")
    public List<CategoryAdminListResponse> getNestedCategories(@PathVariable Long id) {
        return categoryAdminService.getNestedCategories(id);
    }
}
