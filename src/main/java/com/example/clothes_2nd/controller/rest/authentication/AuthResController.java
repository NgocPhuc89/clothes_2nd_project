package com.example.clothes_2nd.controller.rest.authentication;

import com.example.clothes_2nd.repository.UserRepository;
import com.example.clothes_2nd.service.admin.user.requets.UserSaveRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthResController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSaveRequest request) {
        //trả ra được JWT
        var users = userRepository.findAll();
        for(var item : users){
            if(item.getUsername().equals(request.getUsername()) && item.getPassword().equals(request.getPassword())){
                String token = jwtToken(item.getUsername());
                return ResponseEntity.ok(token);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên đăng nhập hoặc mật khẩu không đúng");
    }

    private String jwtToken(String username) {
        long expiredTime = 3600000;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .compact();
    }

}
