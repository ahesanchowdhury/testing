package com.xworkz.profile.service;

import com.xworkz.profile.entity.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity userEntity);

    List<UserEntity> findAll();
}
