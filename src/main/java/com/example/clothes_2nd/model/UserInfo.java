package com.example.clothes_2nd.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    @OneToMany(mappedBy = "userInfo")
    private List<LocationRegion> locationRegion;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @OneToMany(mappedBy = "userInfo" , cascade = CascadeType.ALL)
    private List<Cart> cartList;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Product> productList;
}
