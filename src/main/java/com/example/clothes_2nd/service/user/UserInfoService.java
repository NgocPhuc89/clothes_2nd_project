package com.example.clothes_2nd.service.user;

import com.example.clothes_2nd.dto.request.UserInfoSaveRequest;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.LocationRegion;
import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.repository.UserLocationRepository;
import com.example.clothes_2nd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserInfoService implements IUserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLocationRepository userLocationRepository;

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public UserInfoSaveRequest create(UserInfoSaveRequest userInfoSaveRequest) {
        User user = new User();
        user.setUsername(userInfoSaveRequest.getUsername());
        user.setPassword(userInfoSaveRequest.getPassword());
        user.setAvatar(File.builder().id(Long.valueOf(userInfoSaveRequest.getAvatarId())).build());
        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setFullName(userInfoSaveRequest.getFullName());
        userInfo.setEmail(userInfoSaveRequest.getEmail());
        userInfo.setPhone(userInfoSaveRequest.getPhone());
        userInfo.setGender(userInfoSaveRequest.getGender());
        userInfoRepository.save(userInfo);

        LocationRegion locationRegion = new LocationRegion();
        locationRegion.setProvinceId(userInfoSaveRequest.getProvinceId());
        locationRegion.setProvinceName(userInfoSaveRequest.getProvinceName());
        locationRegion.setDistrictId(userInfoSaveRequest.getDistrictId());
        locationRegion.setDistrictName(userInfoSaveRequest.getDistrictName());
        locationRegion.setWardId(userInfoSaveRequest.getWardId());
        locationRegion.setWardName(userInfoSaveRequest.getWardName());
        userLocationRepository.save(locationRegion);
        return null;
    }

    public UserInfo save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    @Override
    public void delete(UserInfo userInfo) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public UserInfo saveReturn(UserInfo p) {
        UserInfo pSave = userInfoRepository.save(p);
        return pSave;
    }

    @Override
    public UserInfo createUserInfo(User user, UserInfoSaveRequest userInfoSaveRequest) {
        return null;
    }

}
