package com.example.clothes_2nd.service.home.cartHome.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailHomeResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal total;
    private Long quantity;
    private String imageUrl;
}
