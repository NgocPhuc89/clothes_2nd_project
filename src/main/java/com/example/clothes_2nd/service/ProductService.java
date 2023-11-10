package com.example.clothes_2nd.service;

import com.example.clothes_2nd.dto.request.ProductSaveRequest;
import com.example.clothes_2nd.dto.response.ProductListResponse;
import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUntil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductListResponse> findAllWithSearchEveryThingAndPaging(String search, Pageable pageable) {
        search = "%" + search + "%";
        return productRepository
                .searchEverything(search, pageable)
                .map(product -> {
                    var response = AppUntil.mapper.map(product, ProductListResponse.class);
                    response.setCategory(product.getCategory().getName());
                    return response;
                });
    }

}
