package com.example.clothes_2nd.service.admin.product.response;

import com.example.clothes_2nd.model.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


        import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductListResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private File image;
    private String imgUrl;
    private String category;
    private String size;
    private String status;

}
