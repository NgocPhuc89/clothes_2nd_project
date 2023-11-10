package com.example.clothes_2nd.service.user;

import com.example.clothes_2nd.dto.request.UserInfoSaveRequest;
import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.IGeneralService;


public interface IUserInfoService extends IGeneralService<UserInfo,Long> {

    UserInfoSaveRequest create(UserInfoSaveRequest userInfoSaveRequest);

    UserInfo save(UserInfo userInfo);

    UserInfo saveReturn(UserInfo userInfo);

    UserInfo createUserInfo(User user, UserInfoSaveRequest userInfoSaveRequest);

}
