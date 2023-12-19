package com.example.clothes_2nd.service.admin.cart;
import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.repository.StatusRepository;
import com.example.clothes_2nd.service.admin.cart.response.CartAdminResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartListResponse;
import com.example.clothes_2nd.service.admin.cart.response.CartQuarterlyResponse;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final StatusRepository statusRepository;


    public List<CartListResponse> ProductsSoldDay(){
        List<CartListResponse> cartListRepo = new ArrayList<>();
        for(var item : cartRepository.productsSoldDay()){
            CartListResponse cart = AppUtil.mapper.map(item, CartListResponse.class);
            cartListRepo.add(cart);
        }
        return cartListRepo;
    }

    //tính doanh thu ngày hôm nay so với hôm qua
    public Float PercentTheDay(){
        Float totalToday = cartRepository.percentTheDay();
        Float totalYesterday = cartRepository.percentYesterday();

            return ((totalToday - totalYesterday) / totalYesterday) * 100;
        }

//    tính doanh thu theo quý
    public CartQuarterlyResponse QuarterlyRevenue(){
        return cartRepository.quarterlyRevenue();
    }

    public List<CartAdminResponse> getAllCartAdmin(){
        List<CartAdminResponse> result = new ArrayList<>();
        for (var item : cartRepository.findAll()){
            CartAdminResponse cartAdminResponse = AppUtil.mapper.map(item, CartAdminResponse.class);
            List<String> product = (item.getCartDetails().stream()
                    .map(e-> e.getProduct().getName()).collect(Collectors.toList()));
            cartAdminResponse.setProductNames(product);
            result.add(cartAdminResponse);
        }
        return result;
    }

    public Page<CartAdminResponse> searchNameAndPhone(String search, String statusId, Pageable pageable) {
        search = "%" + search + "%";
        Long id = null;
        if(!Objects.equals(statusId, "")){
            id = Long.valueOf(statusId);
        }
        return cartRepository
                .searchNameAndPhoneByCart(search,id, pageable)
                .map(product -> {
                    var response = AppUtil.mapper.map(product, CartAdminResponse.class);
                    response.setProductNames(product
                            .getCartDetails().stream()
                            .map(e -> e.getProduct().getName()).collect(Collectors.toList()));
                    return response;
                });
    }

    public CartAdminResponse updateStatus(Long cardId, Long statusId){
        Optional<Cart> optionalCart = cartRepository.findById(cardId);
        if(optionalCart.isEmpty()){
            throw new RuntimeException("Cart không tồn tại!!!");
        }
        Cart cart = optionalCart.get();
        Optional<Status> statusOptional = statusRepository.findStatusById(statusId);
        if(statusOptional.isEmpty()){
            throw new RuntimeException("Status không tồn tại!!!");
        }
        Status status = statusOptional.get();
        cart.setStatus(status);
        Cart cartUpdate =  cartRepository.save(cart);
        CartAdminResponse cartAdminResponse = AppUtil.mapper.map(cartUpdate, CartAdminResponse.class);
        return cartAdminResponse;
    }
//    public List<CartAdminResponse> getAllCartAdmin(){
//        List<CartAdminResponse> result = new ArrayList<>();
////        var list = cartRepository.findAll();
//        for (var item : cartRepository.findAll()){
//            CartAdminResponse cartAdminResponse = AppUtil.mapper.map(item, CartAdminResponse.class);
//            List<String> product = (item.getCartDetails().stream()
//                    .map(e-> e.getProduct().getName()).collect(Collectors.toList()));
//            cartAdminResponse.setProductNames(product);
//            result.add(cartAdminResponse);
//        }
//        return result;
//    }
    public List<CartAdminResponse> getStatusById(Long id){
    List<CartAdminResponse> result = new ArrayList<>();
    for (var item : cartRepository.findCartByStatusId(id)){
        CartAdminResponse cartAdminResponse = AppUtil.mapper.map(item, CartAdminResponse.class);
        List<String> product = (item.getCartDetails().stream()
                .map(e-> e.getProduct().getName()).collect(Collectors.toList()));
        cartAdminResponse.setProductNames(product);
        result.add(cartAdminResponse);
    }
    return result;
}
}
