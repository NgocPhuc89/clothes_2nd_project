package com.example.clothes_2nd.dto.request;

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
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private LocationRegion locationRegion;
}
