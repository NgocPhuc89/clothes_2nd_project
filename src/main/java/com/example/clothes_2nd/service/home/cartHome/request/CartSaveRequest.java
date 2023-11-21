package com.example.clothes_2nd.service.home.cartHome.request;

import com.example.clothes_2nd.model.CartDetail;
import com.example.clothes_2nd.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartSaveRequest {
    private String name;
    private BigDecimal totalPrice;
    private String status;
    private String phone;
    private List<CartDetail> cartDetailList;
    private LocationRegion locationRegion;
}
