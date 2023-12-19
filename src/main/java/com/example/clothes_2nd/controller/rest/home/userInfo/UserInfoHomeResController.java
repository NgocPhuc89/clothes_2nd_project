package com.example.clothes_2nd.controller.rest.home.userInfo;

import com.example.clothes_2nd.service.admin.user.response.UserInfoResponse;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeByUserResponse;
import com.example.clothes_2nd.service.home.userInfoHome.UserInfoHomeService;
import com.example.clothes_2nd.service.home.userInfoHome.request.UserInfoHomeSaveRequest;
import com.example.clothes_2nd.service.home.userInfoHome.response.UserInfoHomeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/home/userInfos")
public class UserInfoHomeResController {
    private final UserInfoHomeService userInfoHomeService;

    @PostMapping
    public UserInfoHomeResponse findByEmail(@RequestBody UserInfoHomeSaveRequest request ){
        return userInfoHomeService.findByUserInfo(request);
    }

    @PostMapping("/showCartByUser")
    public List<CartHomeByUserResponse> showCartByUser(@RequestBody UserInfoHomeSaveRequest request ){
        return userInfoHomeService.showCartByUser(request);
    }
}
