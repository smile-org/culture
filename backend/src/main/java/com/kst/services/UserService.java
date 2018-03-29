package com.kst.services;

import com.kst.entities.User;

import java.util.Map;

public interface UserService {

    Map login(String userName,String password);
}
