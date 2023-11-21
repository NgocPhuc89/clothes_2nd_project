package com.example.clothes_2nd.service.home.cartHome;

import com.example.clothes_2nd.model.*;
import com.example.clothes_2nd.repository.*;
import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailSaveRequest;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import com.example.clothes_2nd.service.home.cartHome.request.CartSaveRequest;
import com.example.clothes_2nd.service.admin.location.request.LocationRegionSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CartHomeService {
    private final LocationRegionRepository locationRegionRepository;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDetailRepository cartDetailRepository;
    private final StatusRepository statusRepository;

    public Cart checkOut(CartSaveRequest request) {

        Cart cart = AppUtil.mapper.map(request, Cart.class);
        LocationRegion locationRegion = request.getLocationRegion();;

        locationRegionRepository.save(locationRegion);

        cart.setLocationRegion(locationRegion);
        cartRepository.save(cart);

        return cart;
    }

    public Cart addToCart(CartDetailSaveRequest request) {
        Optional<Product> productById = productRepository.findById(request.getId());
        Optional<Cart> cartById = cartRepository.findById(2L);
        Optional<UserInfo> userInfoById = userInfoRepository.findById(1L);
        Optional<Status> statusById = statusRepository.findById(1L);
        Product product = productById.get();


        if (cartById.isPresent()) {
            Cart cart = cartById.get();

            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(product);
            cartDetail.setCart(cart);
            cartDetail.setQuantity(1L);
            cartDetail.setPrice(productById.orElseThrow().getPrice());
            cartDetail.setTotal(cartDetail.getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
            cartDetailRepository.save(cartDetail);

            cart.getCartDetails().add(cartDetail);
            cart.setTotalPrice(cart.getTotalPrice().add(cartDetail.getTotal()));
            cartRepository.save(cart);
            return cart;
        } else {
            Cart newCart = new Cart();
            UserInfo userInfo = userInfoById.get();
            Status status = statusById.get();

            newCart.setUserInfo(userInfo);

            CartDetail newCartDetail = new CartDetail();
            newCartDetail.setProduct(product);
            newCartDetail.setCart(newCart);
            newCartDetail.setQuantity(1L);
            newCartDetail.setPrice(productById.orElseThrow().getPrice());
            newCartDetail.setTotal(newCartDetail.getPrice().multiply(BigDecimal.valueOf(newCartDetail.getQuantity())));
            cartDetailRepository.save(newCartDetail);

            newCart.setTotalPrice(newCartDetail.getTotal());
            newCart.setStatus(status);

            cartRepository.save(newCart);
            return newCart;
        }
    }

    public CartHomeResponse  findAllByUser() {
        //find Cart có status Giỏ Hàng, có cái id user;
        // map qua dto
        // trả về
        var result = new CartHomeResponse();
       Cart cart = cartRepository.findByUserInfo_IdAndStatus_Id(1L, 1L).orElse(new Cart());
       if(cart.getCartDetails() == null || cart.getCartDetails().size() == 0){
           return result;
       }

       for (var cartDetail : cart.getCartDetails()){
           var productDetail = AppUtil.mapper.map(cartDetail, CartDetailHomeResponse.class);
           productDetail.getProduct().setListFile(cartDetail.getProduct().getFiles().stream().map(File::getUrl).collect(Collectors.toList()));
           result.getListCartDetail().add(productDetail);
       }
       result.setTotal(cart.getTotalPrice());
       return result;
    }


}
