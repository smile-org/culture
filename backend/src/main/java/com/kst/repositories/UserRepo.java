package com.kst.repositories;

import com.kst.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepo {

    User getUser(String userName, String password);
}
