package com.example.clothes_2nd.service.admin.product;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.repository.CategoryRepository;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.service.admin.product.request.ProductSaveRequest;
import com.example.clothes_2nd.service.admin.product.request.SelectOptionRequest;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Product findProductById(Long id) {
        Product product =productRepository.findById(id).orElse(null);
        return product;
    }

    public ResponseEntity<?> createProducts(ProductSaveRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSize(Size.valueOf(request.getSize()));
        product.setStatus(request.getStatus());
        SelectOptionRequest categoryRequest = request.getCategory();
        if (categoryRequest != null) {
            Category category = categoryRepository.findById(Long.valueOf(categoryRequest.getId())).orElse(null);
            product.setCategory(category);

        }
        productRepository.save(product);
        List<File> fileList = request.getFiles();
        for (File file : fileList) {
            file.setProduct(product);
        }
        fileRepository.saveAll(fileList);
        return ResponseEntity.ok("ok!");
    }

    public ResponseEntity<?> updateProduct(ProductSaveRequest request, Long id) {
        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setSize(Size.valueOf(request.getSize()));
        newProduct.setStatus(request.getStatus());
        SelectOptionRequest categoryRequest = request.getCategory();
        if (categoryRequest != null) {
            Category category = categoryRepository.findById(Long.valueOf(categoryRequest.getId())).orElse(null);
            newProduct.setCategory(category);

        }
        List<File> fileList = request.getFiles();
        for (File file : fileList) {
            file.setId(id);
            file.setProduct(newProduct);
        }
        fileRepository.saveAll(fileList);
        productRepository.save(newProduct);
        return ResponseEntity.ok("ok!");
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
