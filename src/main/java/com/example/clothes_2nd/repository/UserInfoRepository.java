package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.service.admin.user.response.UserInfoResponse;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT NEW com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse (" +
            "ui.id," +
            "ui.user.id," +
            "u.username," +
            "u.password," +
            "u.avatar.id," +
            "ui.fullName," +
            "ui.email," +
            "ui.phone," +
            "ui.gender," +
            "lg.provinceId," +
            "lg.provinceName," +
            "lg.districtId," +
            "lg.districtName," +
            "lg.wardId," +
            "lg.wardName," +
            "lg.address " +
            ") " +
            "FROM UserInfo AS ui " +
            "JOIN User AS u " +
            "ON ui.user.id = u.id " +
            "JOIN LocationRegion AS lg " +
            "ON lg.userInfo.id = ui.id " +
            "WHERE ui.id = :id"
    )
    Optional<UserInfoSaveResponse> getUserInfoById(Long id);

    @Query("SELECT NEW com.example.clothes_2nd.service.admin.user.response.UserInfoResponse (" +
            "ui.id," +
            "ui.user.id," +
            "u.username," +
            "u.password," +
            "u.avatar.id," +
            "u.avatar.url," +
            "ui.fullName," +
            "ui.email," +
            "ui.phone," +
            "ui.gender," +
            "lg.provinceId," +
            "lg.provinceName," +
            "lg.districtId," +
            "lg.districtName," +
            "lg.wardId," +
            "lg.wardName," +
            "lg.address " +
            ") " +
            "FROM UserInfo AS ui " +
            "JOIN User AS u " +
            "ON ui.user.id = u.id " +
            "JOIN LocationRegion AS lg " +
            "ON lg.userInfo.id = ui.id " +
            "WHERE ui.id = :id"
    )
    Optional<UserInfoResponse> getUserById(Long id);

    Optional<UserInfo> findUserInfoByUserId(Long userId);
    @Query("SELECT NEW com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse (" +
            "ui.id," +
            "ui.user.id," +
            "u.username," +
            "u.password," +
            "u.avatar.id," +
            "ui.fullName," +
            "ui.email," +
            "ui.phone," +
            "ui.gender," +
            "lg.provinceId," +
            "lg.provinceName," +
            "lg.districtId," +
            "lg.districtName," +
            "lg.wardId," +
            "lg.wardName," +
            "lg.address " +
            ") " +
            "FROM UserInfo AS ui " +
            "JOIN User AS u " +
            "ON ui.user.id = u.id " +
            "JOIN LocationRegion AS lg " +
            "ON lg.userInfo.id = ui.id " +
            "WHERE ui.fullName LIKE %:search% " +
            "OR ui.phone LIKE %:search% " +
            "OR ui.email LIKE %:search% " +
            "OR u.username LIKE %:search% " +
            "OR lg.provinceName LIKE %:search% " +
            "OR lg.districtName LIKE %:search% " +
            "OR lg.wardName LIKE %:search% " +
            "OR lg.address LIKE %:search% " +

            "ORDER BY ui.id ASC"
    )
    Page<UserInfoSaveResponse> getAllUserInfo(@Param("search") String search, Pageable pageable);



    boolean existsByEmailIgnoreCase(String email);

    Boolean existsByPhone(String phone);


}
