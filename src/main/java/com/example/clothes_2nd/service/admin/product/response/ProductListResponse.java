package com.example.clothes_2nd.service.admin.product.response;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.service.admin.product.request.SelectOptionRequest;
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
public class ProductListResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<SelectOptionRequest> files;
    private String category;
    private Long categoryParentId;
    private Long categoryGranParentId;
    private Size size;
    private String status;

}
