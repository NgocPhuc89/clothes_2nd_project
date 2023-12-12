package com.example.clothes_2nd.controller.rest.admin.cart;

import com.example.clothes_2nd.service.admin.cart.CartService;
import com.example.clothes_2nd.service.admin.cart.response.CartListResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartQuarterlyResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/cart")
public class CartAdminRestController {
    private final CartService cartService;
    @GetMapping("/sold")
    public List<CartListResponse> ProductsSoldDay(){
        return cartService.ProductsSoldDay();
    }
//    doanh thu ngay
    @GetMapping("/percent")
    public CartResponse PercentTheDay(){

        Float precent = cartService.PercentTheDay();

        CartResponse precentChange = new CartResponse(precent);
        return precentChange;
    }

//    doanh thu theo quý
    @GetMapping("/quarterly")
    public CartQuarterlyResponse QuarterlyRevenue(){
        return  cartService.QuarterlyRevenue();
    }
}
