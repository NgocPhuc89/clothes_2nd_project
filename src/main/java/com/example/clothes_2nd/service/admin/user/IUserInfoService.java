package com.example.clothes_2nd.service.admin.user;

import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.admin.IGeneralService;
import com.example.clothes_2nd.service.admin.user.response.UserInfoResponse;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IUserInfoService extends IGeneralService<UserInfo,Long> {

    Page<UserInfoSaveResponse> getAllUserInfo(String search , Pageable pageable);

    UserInfoSaveResponse create(UserInfoSaveRequest userInfoSaveRequest);
    UserInfoSaveResponse edit(Long id, UserInfoSaveRequest userInfoSaveRequest);

    UserInfo save(UserInfo userInfo);

    Optional<UserInfoResponse> getUserById(Long id);

}
