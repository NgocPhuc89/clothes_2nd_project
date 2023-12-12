package com.example.clothes_2nd.service.admin.product.request;

import com.example.clothes_2nd.model.File;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSaveRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.00", message = "Giá phải lớn hơn hoặc bằng 0")
    private BigDecimal price;

    @NotBlank(message = "Tình trạng không được để trống")
    private String status;

    @NotBlank(message = "Kích thước không được để trống")
    private String size;

    @Valid
    @NotEmpty(message = "Danh sách loại không được rỗng")
    private SelectOptionRequest category;

    @NotEmpty(message = "Danh sách tệp không được rỗng")
    private List<@Valid SelectOptionRequest> files;
}
