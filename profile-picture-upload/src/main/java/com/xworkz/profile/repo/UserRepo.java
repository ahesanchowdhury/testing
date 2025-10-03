package com.xworkz.profile.repo;

import com.xworkz.profile.entity.UserEntity;

import java.util.List;

public interface UserRepo {
    void save(UserEntity userEntity);

    List<UserEntity> findAll();
}
