package com.example.clothes_2nd.controller.rest.admin.user;

import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.service.admin.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    private IUserService iUserService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {

        List<User> users = iUserService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
