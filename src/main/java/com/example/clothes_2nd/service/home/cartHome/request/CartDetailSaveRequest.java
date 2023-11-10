package com.example.clothes_2nd.service.home.cartHome.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailSaveRequest {
    private Long id;
    private Long quantity;
}
