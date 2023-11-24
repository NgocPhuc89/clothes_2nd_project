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
        Cart cart = cartRepository.findByUserInfo_IdAndStatus_Id(1L, 1L)
                .orElseThrow(() -> new RuntimeException("Chưa có cart"));
        AppUtil.mapper.map(request,cart);
        LocationRegion locationRegion = request.getLocationRegion();
        locationRegion.setUserInfo(cart.getUserInfo());
        locationRegionRepository.save(locationRegion);
        List<CartDetail> cartDetails = cartDetailRepository.findCartDetailByCartId(cart.getId());
        for(CartDetail item : cartDetails){
            item.setQuantity(0L);
            cartDetailRepository.save(item);
            var optionalProduct = productRepository.findById(item.getProduct().getId());
            Product product = optionalProduct.get();
            product.setPaid(true);
            productRepository.save(product);
        }
        cart.setStatus(new Status(2L));
        cart.setLocationRegion(locationRegion);
        cartRepository.save(cart);
        return cart;
    }
    public Cart addToCart(CartDetailSaveRequest request) {
        var product = productRepository.findById(request.getId());
        var userInfo = userInfoRepository.findById(1L);
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product.get());
        cartDetail.setQuantity(1L);
        cartDetail.setPrice(product.orElseThrow().getPrice());
        cartDetail.setTotal(cartDetail.getPrice());
        Cart cart = cartRepository.findByUserInfo_IdAndStatus_Id(1L, 1L)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    cartDetail.setCart(newCart);
                    newCart.setUserInfo(userInfo.get());
                    newCart.setStatus(statusRepository.findAll().get(0));
                    List<CartDetail> cartDetails = new ArrayList<>();
                    cartDetails.add(cartDetail);
                    newCart.setCartDetails(cartDetails);
                    newCart = cartRepository.save(newCart);
                    return newCart;
                });
        var check = cartDetailRepository.existsByCart_IdAndProduct_IdAndCart_Status_Id(cart.getId(), product.get().getId(), cart.getStatus().getId());
        if(check){
            return null;
        }else {
            cartDetail.setCart(cart);
            cartDetailRepository.save(cartDetail);
            cart.getCartDetails().add(cartDetail);
            cart.setTotalPrice(cart.getTotalPrice().add(cartDetail.getTotal()));
            cartRepository.save(cart);
            return cart;
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
            if(cartDetail.getQuantity() != 0){
                var productDetail = AppUtil.mapper.map(cartDetail, CartDetailHomeResponse.class);
                productDetail.getProduct().setListFile(cartDetail.getProduct().getFiles().stream().map(File::getUrl).collect(Collectors.toList()));
                result.getListCartDetail().add(productDetail);
            }
        }
        result.setTotal(cart.getTotalPrice());
        return result;
    }
    public CartHomeResponse removeItem(Long id){
        var result = new CartHomeResponse();
        Cart cart = cartRepository.findByUserInfo_IdAndStatus_Id(1L, 1L).orElse(new Cart());
        if(cart.getCartDetails() == null || cart.getCartDetails().size() == 0){
            return result;
        }
        for(var cartDetail : cart.getCartDetails()){
            if(cartDetail.getProduct().getId() == id){
                cartDetail.setQuantity(0L);
                return result;
            }else {
                var productDetail = AppUtil.mapper.map(cartDetail, CartDetailHomeResponse.class);
                productDetail.getProduct().setListFile(cartDetail.getProduct().getFiles().stream().map(File::getUrl).collect(Collectors.toList()));
                result.getListCartDetail().add(productDetail);
                result.setTotal(cart.getTotalPrice());
            }
        }
        return result;
    }
}