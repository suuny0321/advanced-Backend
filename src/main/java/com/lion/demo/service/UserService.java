package com.lion.demo.service;

import com.lion.demo.entity.User;

import java.util.List;

public interface UserService {
    public static final int CORRECT_LOGIN =0;
    public static final int  WRONG_PASSWORD =1;
    public static final int USER_NOT_EXIST =2;
    User findByUid(String uid); //uid값을 주고 데이터를 받아오는

    List<User> getUsers();//list로 모든 데이터 가져오기

    void registerUser(User user);

    void updateUser(User user);

    void deleteUser(String uid);

    int login(String uid, String pwd);
}
