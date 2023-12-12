package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.service.admin.cart.response.CartListResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartQuarterlyResponse;
import com.example.clothes_2nd.service.admin.revenue.response.RevenueResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserInfo_IdAndStatus_Id(Long userInfo_id, Long status_id);

    Optional<Cart> findByStatus_Id(Long status_id);

//    @Query( value = "SELECT  SUM(orderDate) FROM Cart c WHERE" + "(COALESCE(:#{#request.id}, c.id)=c.id) +
//            "AND c.totalPrice BETWEEN :start AND :end")
//
//
//    List<Cart> calculateRevenue(Long id);
//    @Query("SELECT SUM(c.totalPrice) FROM Cart c WHERE (COALESCE(:#{#request.id}, c.totalPrice) = c.totalPrice)" +
//            " AND c.orderDate BETWEEN :start AND :end")
//    List<Cart> calculateRevenue(@Param("request") CartSaveRequest request, @Param("start") Date start,
//                                @Param("end") Date end);


    @Query(value = "SELECT new com.example.clothes_2nd.service.admin.revenue.response.RevenueResponse" +
            "(SUM(c.totalPrice), DATE(c.orderDate)) FROM Cart c WHERE " +
            "DATE_FORMAT(c.orderDate, '%Y-%m-%d') " +
            " BETWEEN DATE_FORMAT(:start, '%Y-%m-%d') AND DATE_FORMAT(:end, '%Y-%m-%d') group by DATE (c.orderDate) ")
    List<RevenueResponse> calculateRevenue(LocalDate start, LocalDate end);


    @Query(value = "select c from Cart as c where c.status.id = 5 and c.orderDate = CURRENT_DATE ")
    List<Cart> productsSoldDay();

    //tính doanh thu trong hôm nay so với ngày hôm qua
    @Query(value = "SELECT sum (c.totalPrice)" +
            " FROM Cart c WHERE c.status.id = 5 " +
            "AND c.orderDate = CURRENT_DATE ")
    Float percentTheDay();
    @Query(value = "SELECT sum (c.totalPrice)" +
            " FROM Cart c WHERE c.status.id = 5 " +
            "AND c.orderDate = CURRENT_DATE - 1 ")
    Float percentYesterday();

//    @Query(value = "SELECT new com.example.clothes_2nd.service.admin.cart.response.CartQuarterlyResponse" +
//            "(YEAR(c.orderDate) , QUARTER(c.orderDate) , coalesce(SUM(c.totalPrice), 0)) " +
//            "FROM Cart c " +
//            "WHERE c.status.id = 5 " +
//            "GROUP BY YEAR(c.orderDate), QUARTER(c.orderDate)")

    @Query(value = "SELECT\n" +
            "  COALESCE(SUM(CASE WHEN EXTRACT(QUARTER FROM order_date) = 1 THEN total_price ELSE 0 END), 0) AS q1,\n" +
            "  COALESCE(SUM(CASE WHEN EXTRACT(QUARTER FROM order_date) = 2 THEN total_price ELSE 0 END), 0) AS q2,\n" +
            "  COALESCE(SUM(CASE WHEN EXTRACT(QUARTER FROM order_date) = 3 THEN total_price ELSE 0 END), 0) AS q3,\n" +
            "  COALESCE(SUM(CASE WHEN EXTRACT(QUARTER FROM order_date) = 4 THEN total_price ELSE 0 END), 0) AS q4\n" +
            "FROM Cart WHERE status_id = 5 AND YEAR(order_date) = YEAR(CURRENT_DATE)", nativeQuery = true)
    CartQuarterlyResponse quarterlyRevenue();
}
