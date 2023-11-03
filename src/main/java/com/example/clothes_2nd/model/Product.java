package com.example.clothes_2nd.model;

import com.example.clothes_2nd.model.emun.Size;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<File> files;

    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_userInfo")
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    private Size size;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<CartDetail> cardDetail;
}
