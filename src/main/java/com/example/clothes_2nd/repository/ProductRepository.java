package com.example.clothes_2nd.repository;
import com.example.clothes_2nd.service.home.productHome.request.ProductFilterRequest;
import com.example.clothes_2nd.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "SELECT p FROM Product p WHERE " +
//            " (:#{#request.size} is null or :#{#request.size} = p.size) " +
//            " AND (:#{#request.categoryId} is null or :#{#request.categoryId} = p.category.id)" +
//            " AND (:#{#request.priceMin} is null or :#{#request.priceMin} < p.price)" +
//            " AND (:#{#request.priceMax} is null or :#{#request.priceMax} > p.price)" +
//            " AND p.name like :#{#request.search}"
//    )
//    Page<Product> filterProduct(ProductFilterRequest request, Pageable pageable);
@Query(value = "SELECT p FROM Product p WHERE " +
        "(COALESCE(:#{#request.size}, p.size) = p.size) " +
        "AND (COALESCE(:#{#request.categoryId}, p.category.id) = p.category.id) " +
        "AND (COALESCE(:#{#request.priceMin}, p.price) <= p.price) " +
        "AND (COALESCE(:#{#request.priceMax}, p.price) >= p.price) " +
        "AND p.name LIKE CONCAT('%', :#{#request.search}, '%')")
Page<Product> filterProduct(@Param("request") ProductFilterRequest request, Pageable pageable);
    //Page<Product> findProductBySizeAndPriceBetweenAndCategory_Id(Size size, BigDecimal price, BigDecimal price2, Long category_id, Pageable pageable);


    @Query(value = "SELECT p FROM Product p WHERE " +
            "(p.name like :search or " +
            "p.category.name like :search)")
//
    Page<Product> searchEverything(String search, Pageable pageable);

}
