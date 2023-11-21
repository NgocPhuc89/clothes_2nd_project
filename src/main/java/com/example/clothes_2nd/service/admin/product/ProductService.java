package com.example.clothes_2nd.service.admin.product;

import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductListResponse> findAllWithSearchEveryThingAndPaging(String search, Pageable pageable) {
        search = "%" + search + "%";
        return productRepository
                .searchEverything(search, pageable)
                .map(product -> {
                    var response = AppUtil.mapper.map(product, ProductListResponse.class);
                    response.setCategory(product.getCategory().getName());
                    return response;
                });
    }

}
