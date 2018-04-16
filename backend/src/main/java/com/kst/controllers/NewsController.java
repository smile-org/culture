package com.kst.controllers;

import com.kst.entities.Focus;
import com.kst.entities.News;
import com.kst.entities.User;
import com.kst.services.NewsService;
import com.kst.services.UserService;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;


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




    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public Map addNews(@RequestHeader Map header , @RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }

        News news =new News();
        news.setImage_cn ((String) body.get("image_cn"));
        news.setImage_en ((String) body.get("image_en"));
        news.setTitle_cn ((String) body.get("title_cn"));
        news.setTitle_en ((String) body.get("title_en"));

        news.setLink_cn ((String) body.get("link_cn"));
        news.setLink_en ((String) body.get("link_en"));
        news.setDesc_cn ((String) body.get("desc_cn"));
        news.setDesc_en ((String) body.get("desc_en"));

        news.setContent_cn ((String) body.get("content_cn"));
        news.setContent_en ((String) body.get("content_en"));


        result = newsService.addNews(news);
        logger.debug("Response: " + result);
        return result;
    }



    @RequestMapping(value = "/getNewsNavList", method = RequestMethod.GET)
    public Map getNewsNavList(@RequestHeader Map header) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }



        result = newsService.getNewsNavList();
        logger.debug("Response: " + result);
        return result;
    }

    @RequestMapping(value = "/getNewsByID", method = RequestMethod.GET)
    public Map getNewsByID( int  news_id,  @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }


        result =  newsService.getNewsByID(news_id);
        logger.debug("Response: " + result);
        return result;
    }



    @RequestMapping(value = "/updateNewsByID", method = RequestMethod.POST)
    public Map updateNewsByID(@RequestHeader Map header , @RequestBody Map body) {
        logger.debug("RequestBody: " +  body);
        Map result;
        String token = header.get("token").toString();
        User user = userService.getUserByToken(token);
        if (user == null) {
            result=  MapUtil.generateFailureRes("无效的登录用户");
            return result;
        }

        News news =new News();
        news.setImage_cn ((String) body.get("image_cn"));
        news.setImage_en ((String) body.get("image_en"));
        news.setTitle_cn ((String) body.get("title_cn"));
        news.setTitle_en ((String) body.get("title_en"));

        news.setLink_cn ((String) body.get("link_cn"));
        news.setLink_en ((String) body.get("link_en"));
        news.setDesc_cn ((String) body.get("desc_cn"));
        news.setDesc_en ((String) body.get("desc_en"));

        news.setContent_cn ((String) body.get("content_cn"));
        news.setContent_en ((String) body.get("content_en"));
        news.setNews_id ((Integer) body.get("news_id"));

        result = newsService.updateNewsByID(news);
        logger.debug("Response: " + result);
        return result;
    }



    //-----------------------------------------前台方法---------------------------------------------
    @RequestMapping(value = "/getNewsList", method = RequestMethod.GET)
    public Map getNewsList( String lan, @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result =  newsService.getNewsList(lan);
        logger.debug("Response: " + result);
        return result;
    }



    @RequestMapping(value = "/getNewsInformationByID", method = RequestMethod.GET)
    public Map getNewsInformationByID( String lan, int  news_id,  @RequestHeader Map header ) {
        logger.debug("RequestBody: " +  "null");
        Map result;

        result =  newsService.getNewsInformationByID( lan, news_id);
        logger.debug("Response: " + result);
        return result;
    }




}
