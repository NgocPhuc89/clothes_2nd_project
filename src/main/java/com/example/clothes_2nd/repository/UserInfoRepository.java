package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
