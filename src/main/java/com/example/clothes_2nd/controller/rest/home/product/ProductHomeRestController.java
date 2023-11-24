package com.example.clothes_2nd.controller.rest.home.product;

import com.example.clothes_2nd.service.home.productHome.request.ProductFilterRequest;
import com.example.clothes_2nd.service.home.productHome.response.ProductDetailHomeResponse;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import com.example.clothes_2nd.service.home.productHome.ProductHomeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/home/products")
public class ProductHomeRestController {
    private ProductHomeService productHomeService;

    @GetMapping
    public Page<ProductOfHomeListResponse> findALl(Pageable pageable){
        return productHomeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<ProductDetailHomeResponse> findById(@PathVariable Long id){
        return productHomeService.productDetail(id);
    }

    @GetMapping("/filter")
    public Page<ProductOfHomeListResponse> filter(Pageable pageable , ProductFilterRequest request){
        return productHomeService.filter(pageable,request);
    }
}
