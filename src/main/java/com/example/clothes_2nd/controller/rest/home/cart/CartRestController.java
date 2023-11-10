package com.example.clothes_2nd.controller.rest.home.cart;

import com.example.clothes_2nd.service.home.cartHome.request.CartSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.CartHomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/user/cart")
public class CartRestController {
    private final CartHomeService cartService;
    @PostMapping
    public ResponseEntity<?> create (@RequestBody CartSaveRequest request){
        cartService.create(request);
        return ResponseEntity.ok(request);
    }
}
