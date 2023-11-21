package com.example.clothes_2nd.controller.rest.home.cart;

import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.request.CartSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.CartHomeService;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/home/carts")
public class CartRestController {
    private final CartHomeService cartHomeService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut (@RequestBody CartSaveRequest request){
        cartHomeService.checkOut(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart (@RequestBody CartDetailSaveRequest request){
        cartHomeService.addToCart(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public CartHomeResponse findAllByUser(){
      return  cartHomeService.findAllByUser();
    }

}
