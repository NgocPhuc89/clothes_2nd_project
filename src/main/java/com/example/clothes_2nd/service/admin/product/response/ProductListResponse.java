package com.example.clothes_2nd.service.admin.product.response;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.service.admin.product.request.SelectOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductListResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salesPrice;
    private LocalDateTime depositDate;
    private Boolean active;
    private List<SelectOptionRequest> files;
    private String category;
    private Long categoryParentId;
    private Long categoryGranParentId;
    private Size size;
    private String status;
    private String userInfo;
    private Boolean paid;
    private String fullName;
    private String phone;
    private String codeProduct;
}
