package com.example.clothes_2nd.model;

import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    private BigDecimal salesPrice;
    private Boolean paid;
    private BigDecimal productCode;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<File> files;

    private String status;
    private LocalDateTime depositDate;
    private Boolean active;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_userInfo")
    @JsonIgnore
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    private Size size;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartDetail> cardDetail;
    private String codeProduct;

}
