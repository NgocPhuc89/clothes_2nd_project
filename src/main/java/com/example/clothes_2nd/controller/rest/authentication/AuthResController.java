package com.example.clothes_2nd.controller.rest.authentication;

import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.model.emun.FileType;
import com.example.clothes_2nd.model.emun.Role;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.repository.UserRepository;
import com.example.clothes_2nd.service.admin.user.requets.UserSaveRequest;
import com.example.clothes_2nd.service.home.userHome.request.LoginGoogleSaveRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.example.clothes_2nd.model.emun.FileType.IMAGE;
import static com.example.clothes_2nd.model.emun.Role.ROLE_USER;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthResController {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final FileRepository fileRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserSaveRequest request) {
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

    @PostMapping("/loginGoogle")
    public ResponseEntity<?> loginGoogle(@RequestBody LoginGoogleSaveRequest request){
        String token = jwtToken(request.getName());
        if (!userInfoRepository.existsByEmailIgnoreCase(request.getEmail())) {
            File file = new File();
            file.setUrl(request.getPicture());
            file.setFileType(FileType.IMAGE);
            fileRepository.save(file);

            User user = new User();
            user.setRole(Role.ROLE_USER);
            user.setAvatar(file);
            userRepository.save(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(request.getEmail());
            userInfo.setFullName(request.getName());
            userInfo.setUser(user);
            userInfoRepository.save(userInfo);
        }
        return ResponseEntity.ok(token);
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
