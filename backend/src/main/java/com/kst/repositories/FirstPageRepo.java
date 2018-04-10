package com.kst.repositories;

import com.kst.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FirstPageRepo {

   // User getUser(@Param("userName") String userName, @Param("password") String password);

    List<NavNode> getBannerIDList();

   Banner  getBannerByID(int banner_id);

    void updateBannerByID(Banner banner);

    List<Banner>  getBannerList();

    List<NavNode>   getModuleIDList();


    Module  getModuleByID(int module_id );

     void   updateModuleByID(Module  module);

     List<Module>  getModuleList();

    List<NavNode> getFocusIDList();

    Focus  getFocusByID(int  focus_id);

    void  updateFocusByID(Focus  focus);

    List<Focus>    getFocusList();
}
