package com.example.clothes_2nd.dto.request;

import com.example.clothes_2nd.dto.response.SelectOptionRequest;
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
    private SelectOptionRequest categories;
    private List<SelectOptionRequest> images;
}
