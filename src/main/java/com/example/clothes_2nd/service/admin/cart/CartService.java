package com.example.clothes_2nd.service.admin.cart;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.service.admin.cart.response.CartAdminResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartListResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartQuarterlyResponse;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<CartAdminResponse> getAllCartAdmin(){
        List<CartAdminResponse> result = new ArrayList<>();
        for (var item : cartRepository.findAll()){
            CartAdminResponse cartAdminResponse = AppUtil.mapper.map(item, CartAdminResponse.class);
            result.add(cartAdminResponse);
        }
        return result;
    }
//
//    public Pageable<CartAdminResponse> searchCart(Pageable pageable){
//        Pageable<CartAdminResponse> result = new ArrayList<>();
//        for (var item : cartRepository.searchCartByNameAndPhone()){
//            CartAdminResponse c = AppUtil.mapper.map(item, CartAdminResponse.class);
//
//            result.add(c);
//        }
//        return  result;
//    }

    public Page<CartAdminResponse> searchNameAndPhone(String search, Pageable pageable) {
        search = "%" + search + "%";
        return cartRepository
                .searchNameAndPhoneByCart(search, pageable)
                .map(product -> {
                    var response = AppUtil.mapper.map(product, CartAdminResponse.class);
                    return response;
                });
    }
}
