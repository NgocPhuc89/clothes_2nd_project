package com.example.clothes_2nd.service.home;

import com.example.clothes_2nd.dto.request.CartSaveRequest;
import com.example.clothes_2nd.dto.request.LocationRegionSaveRequest;
import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.model.LocationRegion;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.repository.LocationRegionRepository;
import com.example.clothes_2nd.util.AppUntil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CartHomeService {
    private final LocationRegionRepository locationRegionRepository;
    private final CartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart create(CartSaveRequest request){
        Cart cart = AppUntil.mapper.map(request, Cart.class);
        cart.setOrderDate(LocalDate.now());

        LocationRegionSaveRequest locationRegionRequest = request.getLocationRegion();
        LocationRegion locationRegion = AppUntil.mapper.map(locationRegionRequest, LocationRegion.class);

        locationRegionRepository.save(locationRegion);

        cart.setLocationRegion(locationRegion);
        cartRepository.save(cart);

        return cart;
    }
}
