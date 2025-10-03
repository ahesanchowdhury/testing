package com.xworkz.profile.service;

import com.xworkz.profile.entity.UserEntity;
import com.xworkz.profile.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(UserEntity userEntity) {
        userRepo.save(userEntity);


    }

    @Override
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }
}
