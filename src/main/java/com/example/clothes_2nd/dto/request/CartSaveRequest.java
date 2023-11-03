package com.example.clothes_2nd.dto.request;

import com.example.clothes_2nd.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartSaveRequest {
    private String name;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String status;
    private String phone;
    private BigDecimal shippingFee;
    private LocationRegion locationRegion;
}
