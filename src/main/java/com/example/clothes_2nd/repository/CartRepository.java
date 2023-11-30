package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.service.home.cartHome.request.CartSaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserInfo_IdAndStatus_Id(Long userInfo_id, Long status_id);

//    @Query( value = "SELECT  SUM(orderDate) FROM Cart c WHERE" + "(COALESCE(:#{#request.id}, c.id)=c.id) +
//            "AND c.totalPrice BETWEEN :start AND :end")
//
//
//    List<Cart> calculateRevenue(Long id);
//    @Query("SELECT SUM(c.totalPrice) FROM Cart c WHERE (COALESCE(:#{#request.id}, c.totalPrice) = c.totalPrice)" +
//            " AND c.orderDate BETWEEN :start AND :end")
//    List<Cart> calculateRevenue(@Param("request") CartSaveRequest request, @Param("start") Date start,
//                                @Param("end") Date end);

    @Query("SELECT c FROM Cart c WHERE c.orderDate BETWEEN :start AND :end")
    List<Cart> calculateRevenue(@Param("start") LocalDate start, @Param("end") LocalDate end);


}
