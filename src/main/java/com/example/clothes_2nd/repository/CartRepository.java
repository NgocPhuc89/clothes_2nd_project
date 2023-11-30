package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserInfo_IdAndStatus_Id(Long userInfo_id, Long status_id);

    Optional<Cart> findByStatus_Id(Long status_id);

}
