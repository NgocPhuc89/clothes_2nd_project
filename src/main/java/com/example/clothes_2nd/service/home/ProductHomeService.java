package com.example.clothes_2nd.service.home;

import com.example.clothes_2nd.dto.request.ProductFilterRequest;
import com.example.clothes_2nd.dto.response.home.ProductDetailHomeResponse;
import com.example.clothes_2nd.dto.response.home.ProductOfHomeListResponse;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUntil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductHomeService {
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;
    public Page<ProductOfHomeListResponse> findAll(Pageable pageable){
        return  productRepository.findAll(pageable)
                .map(product -> {
                    var result  = AppUntil.mapper.map(product, ProductOfHomeListResponse.class);
                    result.setImageUrl(product.getFiles().size() > 0 ?
                                    product.getFiles().get(0).getUrl() : "" );
                    return result;
                });
    }

    public Optional<ProductDetailHomeResponse> findById(Long id){
        return productRepository.findById(id)
                .map(e -> {
                    var result = AppUntil.mapper.map(e, ProductDetailHomeResponse.class);
                    result.setListFile(e.getFiles().stream().map(File::getUrl).toList());
                    return result;
                });
    }

    public Page<ProductOfHomeListResponse> filter(Pageable pageable , ProductFilterRequest request){
        return  productRepository.filterProduct(request, pageable)
                .map(e -> {
                    var result = AppUntil.mapper.map(e, ProductOfHomeListResponse.class);
                    result.setImageUrl(e.getFiles().size() > 0 ?
                            e.getFiles().get(0).getUrl() : "" );
                    return result;
                });
    }
}
