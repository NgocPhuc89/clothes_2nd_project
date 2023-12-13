package com.example.clothes_2nd.service.admin.product;
import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.repository.CategoryRepository;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.service.admin.category.response.CategoryAdminListResponse;
import com.example.clothes_2nd.service.admin.product.request.ProductSaveRequest;
import com.example.clothes_2nd.service.admin.product.request.SelectOptionRequest;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FileRepository fileRepository;
    private final CategoryRepository categoryRepository;


    public Page<ProductListResponse> findAllWithSearchEveryThingAndPaging(String search, Pageable pageable) {
        search = "%" + search + "%";
        return productRepository
                .searchEverything(search, pageable)
                .map(product -> {
                    var response = AppUtil.mapper.map(product, ProductListResponse.class);
                    response.setCategory(product.getCategory().getName());
                    return response;
                });
    }
    
    public ProductListResponse findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductListResponse productListResponse = AppUtil.mapper.map(product, ProductListResponse.class);
            productListResponse.setSize(product.getSize());

            // Lấy Category của Product
            Category productCategory = product.getCategory();

            if (productCategory != null) {
                productListResponse.setCategory(String.valueOf(productCategory.getId()));

                Category parentCategory = productCategory.getCategoryParent();
                if (parentCategory != null) {
                    productListResponse.setCategoryParentId(parentCategory.getId());

                    Category granParentCategory = parentCategory.getCategoryParent();
                    if (granParentCategory != null) {
                        productListResponse.setCategoryGranParentId(granParentCategory.getId());
                    }
                }
            }
            
            List<SelectOptionRequest> fileSelectOptions = product.getFiles().stream()
                    .map(file -> {
                        SelectOptionRequest selectOption = new SelectOptionRequest();
                        selectOption.setId(Long.valueOf(file.getId()));
                        selectOption.setUrl(file.getUrl());
                        return selectOption;
                    })
                    .collect(Collectors.toList());

            productListResponse.setFiles(fileSelectOptions);

            return productListResponse;
        } else {
            return null;
        }
    }


    public ProductListResponse createProducts(ProductSaveRequest request) {
        var newProduct = AppUtil.mapper.map(request, Product.class);
        Long categoryId = request.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).get();
        newProduct.setFiles(null);
        newProduct.setCategory(category);
        productRepository.save(newProduct);
        var images = fileRepository.findAllById(request.getFiles().stream().map(e -> Long.valueOf(e.getId())).collect(Collectors.toList()));


        for (var image: images) {
            image.setProduct(newProduct);
        }
        fileRepository.saveAll(images);

        ProductListResponse productListResponse = new ProductListResponse();
        productListResponse.setId(newProduct.getId());
        productListResponse.setDescription(newProduct.getDescription());
        productListResponse.setName(newProduct.getName());
        productListResponse.setPrice(newProduct.getPrice());
        productListResponse.setSize(newProduct.getSize());
        productListResponse.setStatus(newProduct.getStatus());
        productListResponse.setCategory(newProduct.getCategory().getName());


        return productListResponse;
    }


    public ProductListResponse updateProduct(ProductSaveRequest request, Long id) {
        List<File> existingImages = fileRepository.findByProductId(id);
        for (File existingImage : existingImages) {
            existingImage.setProduct(null);
        }
        fileRepository.saveAll(existingImages);

        var updatedProduct = AppUtil.mapper.map(request, Product.class);
        Long categoryId = request.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).get();
        updatedProduct.setId(id);
        updatedProduct.setFiles(null);
        updatedProduct.setCategory(category);
        productRepository.save(updatedProduct);

        List<File> images = fileRepository.findAllById(request.getFiles().stream().map(e -> Long.valueOf(e.getId())).collect(Collectors.toList()));
        for (File image : images) {
            image.setProduct(updatedProduct);
        }
        fileRepository.saveAll(images);
        ProductListResponse productListResponse = AppUtil.mapper.map(updatedProduct, ProductListResponse.class);
        productListResponse.setCategory(updatedProduct.getCategory().getName());

        return productListResponse;
    }



    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }




}
