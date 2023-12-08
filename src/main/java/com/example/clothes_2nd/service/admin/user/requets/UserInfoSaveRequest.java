package com.example.clothes_2nd.service.admin.user.requets;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoSaveRequest {
    @NotBlank(message = "tên người dùng không được để trống")
    @Size(min = 5, max = 30, message = " Tên người dùng phải từ 5 đến 30 ký tự.")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống ")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự")
    private String password;
    private String avatarId;
    @NotBlank(message = "tên người dùng không được để trống")
    @Size(min = 5,max = 30 ,message = "tên người dùng phải từ 5 đến 30 ký tự.")
    private String fullName;
    @NotBlank(message = "Email không được để trống ")
    @Email(message = "đảm bảo rằng là một địa chỉ mail hợp lệ ")
    private String email;
    @NotBlank(message = "số điện thoại không được để trống ")
    private String phone;
    private String gender;
    private String provinceId;
    @NotBlank(message = "tên tĩnh thành không được để trống ")
    private String provinceName;
    private String districtId;
    @NotBlank(message = "tên huyện không được để trống ")
    private String districtName;
    private String wardId;
    @NotBlank(message = " tên phường không được để trống ")
    private String wardName;
    @NotBlank(message = "địa chỉ không được để trống ")
    @Size(min = 4,max = 45,message = "email phải từ 4 đến 45 ký tự .")
    private String address;

    public UserInfoSaveRequest(String username, String password, Long avatarId, String fullName, String email, String phone, String gender, String provinceId, String provinceName, String districtId, String districtName, String wardId, String wardName, String address) {
        this.username = username;
        this.password = password;
        this.avatarId = String.valueOf(avatarId);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}
