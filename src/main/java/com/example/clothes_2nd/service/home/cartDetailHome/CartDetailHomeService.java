package com.example.clothes_2nd.service.home.cartDetailHome;

import com.example.clothes_2nd.service.home.cartHome.response.CartDetailHomeResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUntil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartDetailHomeService {
    private final ProductRepository productRepository;

    public Optional<CartDetailHomeResponse> findById(Long id){
        return productRepository.findById(id)
                .map(product -> {
                    var result = AppUntil.mapper.map(product, CartDetailHomeResponse.class);
                    result.setImageUrl(product.getFiles().size() > 0 ?
                            product.getFiles().get(0).getUrl() : "");
                    return result;
                });
    }
}
