package com.kst.services.impl;

import com.kst.entities.*;
import com.kst.repositories.FirstPageRepo;
import com.kst.repositories.UserRepo;
import com.kst.services.FirstPageService;
import com.kst.services.UserService;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

@Service
public class FirstPageServiceImpl implements FirstPageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FirstPageRepo firstPageRepo;

    @Override
    public Map getBannerIDList() {
        Map result;
        try {
            List<NavNode> idList = firstPageRepo.getBannerIDList();
            result = MapUtil.generateSuccessRes(idList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getBannerByID(int banner_id) {
        Map result;
        try {
             Banner banner  =  firstPageRepo.getBannerByID(banner_id);
            result = MapUtil.generateSuccessRes(banner);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map updateBannerByID(Banner banner) {
        Map result;
        try {
            firstPageRepo.updateBannerByID(banner);
            result = MapUtil.generateSuccessRes("更新成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getBannerList() {
        Map result;
        try {
            List<Banner> banners = firstPageRepo.getBannerList();
            result = MapUtil.generateSuccessRes(banners);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getModuleIDList() {
        Map result;
        try {
            List<NavNode> moduleIDList = firstPageRepo.getModuleIDList();
            result = MapUtil.generateSuccessRes(moduleIDList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getModuleByID(int module_id) {
        Map result;
        try {
             Module  module = firstPageRepo.getModuleByID(module_id);
            result = MapUtil.generateSuccessRes(module);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map updateModuleByID(Module module) {
        Map result;
        try {
            firstPageRepo.updateModuleByID(module);
            result = MapUtil.generateSuccessRes("更新成功");


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getModuleList() {
        Map result;
        try {
            List<Module> moduleList = firstPageRepo.getModuleList();


            result = MapUtil.generateSuccessRes(moduleList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getFocusIDList() {
        Map result;
        try {
            List<NavNode> focusIDList = firstPageRepo.getFocusIDList();


            result = MapUtil.generateSuccessRes(focusIDList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map updateFocusByID(Focus focus) {
        Map result;
        try {
            firstPageRepo.updateFocusByID(focus);


            result = MapUtil.generateSuccessRes("更新成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getFocusList() {
        Map result;
        try {
            List<Focus> focusList = firstPageRepo.getFocusList();


            result = MapUtil.generateSuccessRes(focusList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getFocusByID(int focus_id) {
        Map result;
        try {
            Focus   focus = firstPageRepo.getFocusByID(focus_id);


            result = MapUtil.generateSuccessRes(focus);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    /*
    @Override
    public Map login(String userName, String password) {
        Map result;
        try {
            User user = userRepo.getUser(userName, password);

            if(user != null ) {
                result = MapUtil.generateSuccessRes(user);
            }  else {
                result = MapUtil.generateFailureRes("用户名或密码错误");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    */


}
