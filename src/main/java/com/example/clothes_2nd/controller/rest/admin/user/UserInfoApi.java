package com.example.clothes_2nd.controller.rest.admin.user;

import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.admin.user.IUserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/user/userinfo")
public class UserInfoApi {
    @Autowired
    private IUserInfoService iUserInfoService;


    @GetMapping("")
    public ResponseEntity<List<UserInfo>> getAllUserInfo() {
        List<UserInfo> userInfos = iUserInfoService.findAll();
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserInfo(@RequestBody @Valid UserInfoSaveRequest userInfo) {
        iUserInfoService.create(userInfo);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
}

