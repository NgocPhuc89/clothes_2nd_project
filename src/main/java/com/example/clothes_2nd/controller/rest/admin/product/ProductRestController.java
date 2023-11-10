package com.example.clothes_2nd.controller.rest.admin.product;


import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.service.admin.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductRestController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping
    public Page<ProductListResponse>  getAllProducts(@RequestParam(value ="search",required = false)
                                                         String search,
                                                     Pageable pageable) {
        return  productService.findAllWithSearchEveryThingAndPaging(search, pageable);

    }

//    @PostMapping
//    public ResponseEntity<?> createProduct(@RequestBody ProductSaveRequest request) {
//        productService.createProduct(request);
//        return ResponseEntity.noContent().build();
//    }
}
