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

            for (int i = 0; i < idList.size(); i++) {
                NavNode node = idList.get(i);
                String title = "";

                switch (i) {
                    case 0:
                        title = "第一页热图";
                        break;
                    case 1:
                        title = "第二页热图";
                        break;
                    case 2:
                        title = "第三页热图";
                        break;
                    case 3:
                        title = "第四页热图";
                        break;
                    case 4:
                        title = "第五页热图";
                        break;
                    default:
                        break;

                }

                node.setTitle(title);
            }

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
            Banner banner = firstPageRepo.getBannerByID(banner_id);
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
    public Map getBannerList(String lan) {
        Map result;
        try {
            List<Banner> banners = firstPageRepo.getBannerList();


            for (Banner b : banners) {
                if (lan.equals("cn"))
                {
                    b.setTitle_en(null);
                    b.setDesc_en(null);
                    b.setImage_en(null);
                    b.setLink_en(null);
                } else
                {
                    b.setTitle_cn(null);
                    b.setDesc_cn(null);

                    b.setImage_cn(null);
                    b.setLink_cn(null);

                }


            }


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
            Module module = firstPageRepo.getModuleByID(module_id);
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
    public Map getModuleList(String lan) {
        Map result;
        try {
            List<Module> moduleList = firstPageRepo.getModuleList();

            for(Module   m : moduleList ){
                if(lan.equals("cn"))
                {
                    m.setTitle_en(null);
                    m.setDesc_en(null);

                    m.setImage_en(null);
                    m.setLink_en(null);
                }
                else
                {
                    m.setTitle_cn(null);
                    m.setDesc_cn(null);

                    m.setImage_cn(null);
                    m.setLink_cn(null);
                }
            }

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
    public Map getFocusList(String lan) {
        Map result;
        try {
            List<Focus> focusList = firstPageRepo.getFocusList();

            for( Focus  f : focusList)
            {
                if(lan.equals("cn"))
                {
                    f.setTitle_en(null);
                    f.setDesc_en(null);

                    f.setImage_en(null);
                    f.setLink_en(null);
                }
                else
                {
                    f.setTitle_cn(null);
                    f.setDesc_cn(null);

                    f.setImage_cn(null);
                    f.setLink_cn(null);

                }
            }

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
            Focus focus = firstPageRepo.getFocusByID(focus_id);


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
