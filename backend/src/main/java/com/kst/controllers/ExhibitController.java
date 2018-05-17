package com.kst.controllers;

import com.kst.entities.*;
import com.kst.services.ExhibitService;
import com.kst.services.UserService;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/exhibit")
public class ExhibitController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExhibitService   exhibitService ;


    @Autowired
    private UserService userService;



    @RequestMapping(value = "/addExhibit", method = RequestMethod.POST)
    public Map addExhibit(@RequestHeader Map header , @RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }

        Exhibit exhibit =new Exhibit();

        String lan = (String) body.get("lan");

        if(lan.equals("cn"))
        {
            exhibit.setTitle_cn ((String) body.get("title_cn"));
            exhibit.setContent_cn ((String) body.get("content_cn"));
        }
        else
        {
            exhibit.setTitle_en ((String) body.get("title_en"));
            exhibit.setContent_en ((String) body.get("content_en"));
        }

        exhibit.setCategory ((int) body.get("category"));

        exhibit.setStatus ((int) body.get("status"));

        result = exhibitService.addExhibit(exhibit);
        logger.debug("Response: " + result);
        return result;
    }




    @RequestMapping(value = "/getExhibitNavList", method = RequestMethod.GET)
    public Map getExhibitNavList(@RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = exhibitService.getExhibitNavList();
        logger.debug("Response: " + result);
        return result;
    }




    @RequestMapping(value = "/getExhibitEditPageInfoByID", method = RequestMethod.GET)
    public Map getExhibitEditPageInfoByID(Integer exhibit_id ,  @RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result =    exhibitService.getExhibitEditPageInfoByID(exhibit_id);
        logger.debug("Response: " + result);
        return result;
    }



    @RequestMapping(value = "/updateExhibitByID", method = RequestMethod.POST)
    public Map updateExhibitByID(@RequestHeader Map header ,@RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }


        Exhibit exhibit =new Exhibit();

        String lan = (String) body.get("lan");

        if(lan.equals("cn")) {

            exhibit.setTitle_cn((String) body.get("title_cn"));


            exhibit.setContent_cn((String) body.get("content_cn"));
        }
        else {

            exhibit.setTitle_en((String) body.get("title_en"));
            exhibit.setContent_en((String) body.get("content_en"));
        }


        exhibit.setCategory ((int) body.get("category"));

        /*
        Integer  form_id =null;
        Object   form_idObject =   body.get("form_id");
        if(form_idObject !=null)
        {
            form_id =  (Integer) form_idObject ;
        }
        exhibit.setForm_id (form_id);
        */

        exhibit.setStatus ((int) body.get("status"));
        exhibit.setExhibit_id ((int) body.get("exhibit_id"));

        result = exhibitService.updateExhibitByID(exhibit);
        logger.debug("Response: " + result);
        return result;
    }

    //---------------------------------------------------  前台方法---------------------------------------


    @RequestMapping(value = "/getExhibitByID", method = RequestMethod.GET)
    public Map getExhibitByID( String lan,  int exhibit_id, @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result = exhibitService.getExhibitByID(  lan,exhibit_id);
        logger.debug("Response: " + result);
        return result;
    }


    @RequestMapping(value = "/getExhibitFrontNavList", method = RequestMethod.GET)
    public Map getExhibitFrontNavList( String  lan,  @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result = exhibitService.getExhibitFrontNavList(lan);
        logger.debug("Response: " + result);
        return result;
    }



    //------  前台表单提交-------------



   //用于联系我们
    @RequestMapping(value = "/addLXWM", method = RequestMethod.POST)
    public Map addJJH(@RequestHeader Map header , @RequestBody Map body) {
        logger.debug("RequestBody: " +  "null");
        Map result;


        JJH jjh =new JJH();

        jjh.setName ((String) body.get("name"));
        jjh.setPhone ((String) body.get("phone"));

        jjh.setEmail ((String) body.get("email"));
        jjh.setDesc ((String) body.get("desc"));

        result = exhibitService.addJJH(jjh);
        logger.debug("Response: " + result);
        return result;
    }




    @RequestMapping(value = "/addXCBM", method = RequestMethod.POST)
    public Map addCCR(@RequestHeader Map header , @RequestBody Map body) {
        logger.debug("RequestBody: " +  "null");
        Map result;


        CCR ccr =new CCR();

        ccr.setName ((String) body.get("name"));
        ccr.setPhone ((String) body.get("phone"));
        ccr.setDesc ((String) body.get("desc"));

        ccr.setJourney_type ((String) body.get("journey_type"));
        ccr.setMarch ((String) body.get("march"));

        result = exhibitService.addCCR(ccr);
        logger.debug("Response: " + result);
        return result;
    }





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
}
