package com.example.clothes_2nd.api.home;

import com.example.clothes_2nd.dto.request.ProductFilterRequest;
import com.example.clothes_2nd.dto.response.ProductListResponse;
import com.example.clothes_2nd.dto.response.home.ProductDetailHomeResponse;
import com.example.clothes_2nd.dto.response.home.ProductOfHomeListResponse;
import com.example.clothes_2nd.service.home.ProductHomeService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/home/product")
public class ProductHomeRestController {
    private ProductHomeService productHomeService;

    @GetMapping
    public Page<ProductOfHomeListResponse> findALl(Pageable pageable){
        return productHomeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<ProductDetailHomeResponse> findById(@PathVariable Long id){
        return productHomeService.findById(id);
    }

    @GetMapping("/filter")
    public Page<ProductOfHomeListResponse> filter(Pageable pageable , ProductFilterRequest request){
        return productHomeService.filter(pageable,request);
    }
}
