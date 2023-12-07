package com.example.clothes_2nd.controller.rest.admin.product;


import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.service.admin.product.request.ProductSaveRequest;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.service.admin.product.ProductService;
import com.example.clothes_2nd.service.home.productHome.ProductHomeService;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductRestController {
    private ProductHomeService productHomeService;
    private final ProductService productService;

//    @GetMapping()
//    public Page<ProductListResponse>  getAllProducts(@RequestParam(value ="search",required = false)
//                                                         String search,
//                                                     Pageable pageable) {
//        return  productService.findAllWithSearchEveryThingAndPaging(search, pageable);
//    }
    @GetMapping
    public List<ProductListResponse> findAllProduct() {
        return productService.finAllProducts();

    }
    @GetMapping("/{id}")
    public ProductListResponse findAllProductById(@PathVariable Long id) {
      return  productService.findProductById(id);

    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductSaveRequest request) {
        productService.createProducts(request);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping ("/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductSaveRequest request, @PathVariable Long id) {
        productService.updateProduct(request,id);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/count")
    public Page<ProductOfHomeListResponse> count( Pageable pageable){
        return productHomeService.countProduct(pageable);
    }
}
