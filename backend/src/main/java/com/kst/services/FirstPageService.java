package com.kst.services;

import com.kst.entities.Banner;
import com.kst.entities.Focus;
import com.kst.entities.Module;

import java.util.List;
import java.util.Map;

public interface FirstPageService {

    //Map login(String userName, String password);


    Map getBannerIDList();

    Map getBannerByID(int banner_id);

    Map updateBannerByID(Banner banner);

    Map  getBannerList(String lan);

    Map      getModuleIDList();

    Map  getModuleByID(int module_id );

    Map       updateModuleByID(Module module);

    Map getModuleList( String lan);

    Map getFocusIDList();

    Map updateFocusByID(Focus focus);

    Map  getFocusList(String lan);

    Map    getFocusByID(int  focus_id);
}
