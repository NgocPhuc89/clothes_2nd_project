package com.example.clothes_2nd.service.admin.user.requets;

import com.example.clothes_2nd.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoSaveRequest {
    private String username;

    private String password;

    private String avatarId;

    private String fullName;
    private String email;
    private String phone;
    private String gender;

    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
}
