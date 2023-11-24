package com.example.clothes_2nd.controller.rest.admin.user;
import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.service.admin.user.IUserInfoService;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("api/admin/userinfo")
public class UserInfoRestController {

    private final IUserInfoService iUserInfoService;

    public UserInfoRestController(IUserInfoService iUserInfoService) {
        this.iUserInfoService = iUserInfoService;
    }


    @GetMapping()
    public ResponseEntity<?> getAllUserInfo() {
        List<UserInfoSaveResponse> userInfos = iUserInfoService.getAllUserInfo();
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserInfo(@RequestBody @Valid UserInfoSaveRequest userInfo) {

        UserInfoSaveResponse userInfoSaveResponse = iUserInfoService.create(userInfo);
        return new ResponseEntity<>(userInfoSaveResponse, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUserInfo(@PathVariable Long id, @RequestBody @Valid UserInfoSaveRequest userInfo) {
        UserInfoSaveResponse updatedUserInfo = iUserInfoService.edit(id, userInfo);
        return new ResponseEntity<>(updatedUserInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserInfo(@PathVariable Long id) {
        iUserInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

