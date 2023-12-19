package com.example.clothes_2nd.service.home.userInfoHome;

import com.example.clothes_2nd.repository.CartDetailRepository;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeByUserResponse;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;
import com.example.clothes_2nd.service.home.userInfoHome.request.UserInfoHomeSaveRequest;
import com.example.clothes_2nd.service.home.userInfoHome.response.UserInfoHomeResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserInfoHomeService {
    private final UserInfoRepository userInfoRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    public UserInfoHomeResponse findByUserInfo(UserInfoHomeSaveRequest request) {
        var userInfo = userInfoRepository.findUserInfoByEmail(request.getEmail());
        var result = new UserInfoHomeResponse();
        if(userInfoRepository.existsByEmailIgnoreCase(request.getEmail())){
            result = AppUtil.mapper.map(userInfo, UserInfoHomeResponse.class);
            result.setUsername(userInfo.getUser().getUsername());

        }
        return result;
    }

    public List<CartHomeByUserResponse> showCartByUser(UserInfoHomeSaveRequest request){
        List<CartHomeByUserResponse> result = new ArrayList<>();
        var userInfo = userInfoRepository.findUserInfoByEmail(request.getEmail());
        if(userInfoRepository.existsByEmailIgnoreCase(request.getEmail())){
            for(var cart : cartRepository.findCartByUserInfoId(userInfo.getId())){
                var cartByUser = AppUtil.mapper.map(cart, CartHomeByUserResponse.class);
                for (var cartDetail : cartDetailRepository.findCartDetailByCartId(cart.getId())){
                    var cartDetailByUser = AppUtil.mapper.map(cartDetail, CartDetailHomeResponse.class);
                    cartByUser.getCartDetailList().add(cartDetailByUser);
                }
                result.add(cartByUser);
            }
        }
        return  result;
    }

}
