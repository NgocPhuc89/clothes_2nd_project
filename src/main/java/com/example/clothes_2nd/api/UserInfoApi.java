package com.example.clothes_2nd.api;

import com.example.clothes_2nd.dto.request.UserInfoSaveRequest;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.user.IUserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

