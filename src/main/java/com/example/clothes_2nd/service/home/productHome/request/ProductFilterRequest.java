package com.example.clothes_2nd.service.home.productHome.request;

import com.example.clothes_2nd.model.emun.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFilterRequest {
    private Size size;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Long categoryId;
    private String search = "";
}
