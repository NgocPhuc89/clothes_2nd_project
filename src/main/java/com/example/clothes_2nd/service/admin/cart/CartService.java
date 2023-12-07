package com.example.clothes_2nd.service.admin.cart;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.service.admin.cart.response.CartListResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<CartListResponse> ProductsSoldDay(){
        List<CartListResponse> cartListRepo = new ArrayList<>();
        for(var item : cartRepository.ProductsSoldDay()){
            CartListResponse cart = AppUtil.mapper.map(item, CartListResponse.class);
            cartListRepo.add(cart);
        }
        return cartListRepo;
    }
}
