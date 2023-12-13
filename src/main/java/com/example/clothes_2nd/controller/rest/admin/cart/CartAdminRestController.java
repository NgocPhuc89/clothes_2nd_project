package com.example.clothes_2nd.controller.rest.admin.cart;

import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.service.admin.cart.CartService;
import com.example.clothes_2nd.service.admin.cart.response.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/cart")
public class CartAdminRestController {
    private final CartService cartService;
    @GetMapping("/sold")
    public List<CartListResponse> productsSoldDay(){
        return cartService.ProductsSoldDay();
    }

//    doanh thu ngay
    @GetMapping("/percent")
    public CartResponse percentTheDay(){

        Float precent = cartService.PercentTheDay();

        CartResponse precentChange = new CartResponse(precent);
        return precentChange;
    }

//    doanh thu theo quý
    @GetMapping("/quarterly")
    public CartQuarterlyResponse quarterlyRevenue(){
        return  cartService.QuarterlyRevenue();
    }

//    lấy tất cả danh sách cart
    @GetMapping("/list")
    public List<CartAdminResponse> getAllCartAdmin(){
        return  cartService.getAllCartAdmin();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchNameAndPhone(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable;
        if (page < 0) {
            page = 0;
        }
        pageable = PageRequest.of(page, size);
        Page<CartAdminResponse> cartAdminResponses = cartService.searchNameAndPhone(search, pageable);
        return new ResponseEntity<>(cartAdminResponses, HttpStatus.OK);
    }
}