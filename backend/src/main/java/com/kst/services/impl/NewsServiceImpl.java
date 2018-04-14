package com.kst.services.impl;

import com.kst.entities.NavNode;
import com.kst.entities.News;
import com.kst.entities.User;
import com.kst.repositories.NewsRepo;
import com.kst.repositories.UserRepo;
import com.kst.services.NewsService;
import com.kst.services.UserService;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NewsRepo newsRepo;

    @Override
    public Map addNews(News news) {
        Map result;
        try {
            newsRepo.addNews(news);


            result = MapUtil.generateSuccessRes("添加成功");


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }


        return result;
    }

    @Override
    public Map getNewsNavList() {
        Map result;
        try {
            List<NavNode> navNodeList = newsRepo.getNewsNavList();


            result = MapUtil.generateSuccessRes(navNodeList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }


        return result;
    }

    @Override
    public Map updateNewsByID(News news) {
        Map result;
        try {
            newsRepo.updateNewsByID(news);


            result = MapUtil.generateSuccessRes("更新成功");


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }


        return result;
    }

    @Override
    public Map getNewsList(String lan) {
        Map result;
        try {
            List<News> newsList = newsRepo.getNewsList();

            for( News    obj  : newsList)
            {
                if( lan.equals("cn"))
                {
                    obj.setTitle_en(null);
                }
                else
                {
                    obj.setTitle_cn(null);
                }
            }

                result = MapUtil.generateSuccessRes(newsList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }


        return result;
    }

    @Override
    public Map getNewsByID(int news_id) {
        Map result;
        try {
            News news=    newsRepo.getNewsByID(news_id);


                result = MapUtil.generateSuccessRes(news);


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }


        return result;
    }


    @Override
    public Map getNewsInformationByID(  String lan,  int news_id) {
        Map result;
        try {
            News news=    newsRepo.getNewsByID(news_id);



            if(lan.equals("cn"))
            {
                news.setTitle_en(null);

                news.setContent_en(null);
                news.setDesc_en(null);
            }
            else
            {
                news.setTitle_cn(null);

                news.setContent_cn(null);
                news.setDesc_cn(null);
            }

            result = MapUtil.generateSuccessRes(news);


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
