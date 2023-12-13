package com.example.clothes_2nd.service.admin.cart.response;

import com.example.clothes_2nd.model.CartDetail;
import com.example.clothes_2nd.model.LocationRegion;
import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.model.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class CartAdminResponse {
    private Long id;
    private String name;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private LocalDate orderDate = LocalDate.now();
    private String phone;
    private BigDecimal shippingFee;
    private Status status;
    private LocationRegion locationRegion;

    public CartAdminResponse(Long id, String name, BigDecimal totalPrice, LocalDate orderDate, String phone, BigDecimal shippingFee, Status status, LocationRegion locationRegion) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.phone = phone;
        this.shippingFee = shippingFee;
        this.status = status;
        this.locationRegion = locationRegion;
    }
}
