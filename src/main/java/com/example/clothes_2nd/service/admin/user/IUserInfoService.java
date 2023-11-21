package com.example.clothes_2nd.service.admin.user;

import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.admin.IGeneralService;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;

import java.util.List;
import java.util.Optional;


public interface IUserInfoService extends IGeneralService<UserInfo,Long> {

    List<UserInfoSaveResponse> getAllUserInfo();

    UserInfoSaveResponse create(UserInfoSaveRequest userInfoSaveRequest);
    UserInfoSaveResponse edit(Long id, UserInfoSaveRequest userInfoSaveRequest);

    UserInfo save(UserInfo userInfo);

}
