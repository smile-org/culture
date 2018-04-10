package com.kst.repositories;

import com.kst.entities.Banner;
import com.kst.entities.Exhibit;
import com.kst.entities.SelectOptionItem;
import com.kst.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepo {

    User getUser(@Param("userName") String  userName, @Param("password")  String password);

    User  getUserByToken( String token);
}
