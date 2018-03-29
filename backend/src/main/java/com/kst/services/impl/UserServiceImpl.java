package com.kst.services.impl;

import com.kst.entities.User;
import com.kst.repositories.UserRepo;
import com.kst.services.UserService;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;

    @Override
    public Map login(String userName, String password) {
        Map result;
        try {
            User user = userRepo.getUser(userName, password);
            result = MapUtil.generateSuccessRes(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }
}
