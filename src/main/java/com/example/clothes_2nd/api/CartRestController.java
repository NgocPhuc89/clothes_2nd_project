package com.example.clothes_2nd.api;

import com.example.clothes_2nd.dto.request.CartSaveRequest;
import com.example.clothes_2nd.service.home.CartHomeService;
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
