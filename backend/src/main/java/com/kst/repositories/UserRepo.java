package com.kst.repositories;

import com.kst.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepo {

    User getUser(@Param("userName") String  userName, @Param("password")  String password);
}
