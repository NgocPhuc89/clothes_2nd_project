package com.example.clothes_2nd.service.home.cartHome.response;

import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RevenueResponse {
    private BigDecimal totalPrice;
    private LocalDate orderDate;
//    private UserInfo userInfoId;
//    private Status statusId ;
}
