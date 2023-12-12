package com.example.clothes_2nd.service.admin.cart;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.service.admin.cart.response.CartListResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartQuarterlyResponse;
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
        for(var item : cartRepository.productsSoldDay()){
            CartListResponse cart = AppUtil.mapper.map(item, CartListResponse.class);
            cartListRepo.add(cart);
        }
        return cartListRepo;
    }

    //tính doanh thu ngày hôm nay so với hôm qua
    public Float PercentTheDay(){
        Float totalToday = cartRepository.percentTheDay();
        Float totalYesterday = cartRepository.percentYesterday();

            return ((totalToday - totalYesterday) / totalYesterday) * 100;
        }

//    tính doanh thu theo quý
    public CartQuarterlyResponse QuarterlyRevenue(){
        return cartRepository.quarterlyRevenue();
    }
}
