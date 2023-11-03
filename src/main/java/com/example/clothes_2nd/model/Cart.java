package com.example.clothes_2nd.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String status;
    private String phone;
    private BigDecimal shippingFee;
    @OneToOne
    @JoinColumn(name = "locationRegion")
    private LocationRegion locationRegion;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @ManyToOne
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;
}
