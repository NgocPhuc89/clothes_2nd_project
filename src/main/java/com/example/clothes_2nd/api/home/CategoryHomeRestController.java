package com.example.clothes_2nd.api.home;

import com.example.clothes_2nd.dto.response.home.CategoryListResponse;
import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.service.home.CategoryHomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/home/category")
public class CategoryHomeRestController {
    private final CategoryHomeService categoryService;

    @GetMapping
    public List<CategoryListResponse> findAll(){
        return categoryService.findAll();
    }
}
