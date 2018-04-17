package com.kst.controllers;

import com.kst.entities.Banner;
import com.kst.entities.Focus;
import com.kst.entities.Module;
import com.kst.entities.User;
import com.kst.services.FirstPageService;
import com.kst.services.UserService;
import com.kst.utils.Constants;
import com.kst.utils.FileUtil;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/firstPage")
public class FirstPageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FirstPageService firstPageService;

    @Autowired
    private UserService userService;

    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody Map body) {
        logger.debug("RequestBody: " + body);
        String userName = (String) body.get("username");
        String password = (String) body.get("password");
        Map result = userService.login(userName, password);
        logger.debug("Response: " + result);
        return result;
    }
    */



    @RequestMapping(value = "/getBannerNavList", method = RequestMethod.GET)
    public Map getBannerNavList(@RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



         result = firstPageService.getBannerIDList();
        logger.debug("Response: " + result);
        return result;
    }




    @RequestMapping(value = "/getBannerByID", method = RequestMethod.GET)
    public Map getBannerByID(int banner_id ,  @RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = firstPageService.getBannerByID(banner_id);
        logger.debug("Response: " + result);
        return result;
    }

    @RequestMapping(value = "/updateBannerByID", method = RequestMethod.POST)
    public Map updateBannerByID(@RequestHeader Map header ,@RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        Banner  banner =new Banner();

        banner.setStatus ((Integer) body.get("status"));
        banner.setOrder ((Integer) body.get("order"));
        banner.setBanner_id ((Integer) body.get("banner_id"));

        String lan = (String) body.get("lan");
        if(lan.equals("cn"))
        {
            banner.setImage_cn ((String) body.get("image_cn"));
            banner.setLink_cn ((String) body.get("link_cn"));
            banner.setTitle_cn ((String) body.get("title_cn"));
            banner.setDesc_cn ((String) body.get("desc_cn"));
        }
        else
        {
            banner.setImage_en ((String) body.get("image_en"));
            banner.setLink_en ((String) body.get("link_en"));
            banner.setTitle_en ((String) body.get("title_en"));
            banner.setDesc_en ((String) body.get("desc_en"));
        }

        result = firstPageService.updateBannerByID(banner);
        logger.debug("Response: " + result);
        return result;
    }


    @Value("${fileroot}")
    private String fileroot;
    // banner, module, focus, news
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public Map uploadPic(  String  type ,  @RequestHeader Map header ,@RequestParam(value = "file", required = true) MultipartFile file) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }
        try {

            String folder =null ;
            switch (type)
            {
                case "banner" :folder =Constants.bannerFolder;break;
                case "module" :folder =Constants.moduleFolder;break;
                case "focus" :folder =Constants.focusFolder;break;
                case "news" :folder =Constants.newsFolder;break;

                default:break;
            }


            String guid = UUID.randomUUID().toString();
            String fileName =guid +"-"+ file.getOriginalFilename();
            FileUtil.uploadFile(file.getBytes(), fileroot + folder +"/",  fileName);
            String url =  "/"+ folder +"/" + fileName;

            result=  MapUtil.generateSuccessRes(url);


        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            result = MapUtil.generateFailureRes(ex.getMessage());
        }
        logger.debug("Response: " + result);
        return result;
    }





    @RequestMapping(value = "/getModuleNavList", method = RequestMethod.GET)
    public Map getModuleNavList(@RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = firstPageService.getModuleIDList();
        logger.debug("Response: " + result);
        return result;
    }





    @RequestMapping(value = "/getModuleByID", method = RequestMethod.GET)
    public Map getModuleByID(int module_id ,  @RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = firstPageService.getModuleByID(module_id);
        logger.debug("Response: " + result);
        return result;
    }

    @RequestMapping(value = "/updateModuleByID", method = RequestMethod.POST)
    public Map updateModuleByID(@RequestHeader Map header ,@RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }

        Module module =new Module();
        String lan = (String) body.get("lan");

        if(lan.equals("cn"))
        {
            module.setImage_cn ((String) body.get("image_cn"));
            module.setLink_cn ((String) body.get("link_cn"));
            module.setTitle_cn ((String) body.get("title_cn"));
            module.setDesc_cn ((String) body.get("desc_cn"));
        }
        else
        {
            module.setImage_en ((String) body.get("image_en"));
            module.setLink_en ((String) body.get("link_en"));

            module.setTitle_en ((String) body.get("title_en"));
            module.setDesc_en ((String) body.get("desc_en"));
        }

        module.setModule_id ((Integer) body.get("module_id"));

        result = firstPageService.updateModuleByID(module);
        logger.debug("Response: " + result);
        return result;
    }




    @RequestMapping(value = "/getFocusNavList", method = RequestMethod.GET)
    public Map getFocusNavList(@RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = firstPageService.getFocusIDList();
        logger.debug("Response: " + result);
        return result;
    }





    @RequestMapping(value = "/updateFocusByID", method = RequestMethod.POST)
    public Map updateFocusByID(@RequestHeader Map header ,@RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }

        String lan=   (String)body.get("lan");

        Focus focus =new Focus();


        if(lan.equals("cn"))
        {
            focus.setImage_cn ((String) body.get("image_cn"));
            focus.setLink_cn ((String) body.get("link_cn"));
            focus.setTitle_cn ((String) body.get("title_cn"));
            focus.setDesc_cn ((String) body.get("desc_cn"));
        }
        else
        {
            focus.setImage_en ((String) body.get("image_en"));
            focus.setLink_en ((String) body.get("link_en"));
            focus.setTitle_en ((String) body.get("title_en"));
            focus.setDesc_en ((String) body.get("desc_en"));
        }


        focus.setStatus ((Integer) body.get("status"));
        focus.setOrder ((Integer) body.get("order"));
        focus.setFocus_id ((Integer) body.get("focus_id"));

        result = firstPageService.updateFocusByID(focus);
        logger.debug("Response: " + result);
        return result;
    }



    @RequestMapping(value = "/getFocusByID", method = RequestMethod.GET)
    public Map getFocusByID(int focus_id ,  @RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = firstPageService.getFocusByID(focus_id);
        logger.debug("Response: " + result);
        return result;
    }







    //---------------------------------------------------  前台方法---------------------------------------


    @RequestMapping(value = "/getBannerList", method = RequestMethod.GET)
    public Map getBannerList( String lan,   @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result = firstPageService.getBannerList(lan);
        logger.debug("Response: " + result);
        return result;
    }

    @RequestMapping(value = "/getModuleList", method = RequestMethod.GET)
    public Map getModuleList(String lan,  @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result = firstPageService.getModuleList(lan);
        logger.debug("Response: " + result);
        return result;
    }


    @RequestMapping(value = "/getFocusList", method = RequestMethod.GET)
    public Map getFocusList(  String lan,  @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result = firstPageService.getFocusList(lan);
        logger.debug("Response: " + result);
        return result;
    }


}
