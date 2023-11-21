package com.example.clothes_2nd.service.admin.product.request;

import com.example.clothes_2nd.model.File;
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
public class ProductSaveRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String status;
    private String size;
    private SelectOptionRequest category;
    private List<File> files;
}
