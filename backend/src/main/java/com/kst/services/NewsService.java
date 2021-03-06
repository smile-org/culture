package com.kst.services;

import com.kst.entities.NavNode;
import com.kst.entities.News;

import java.util.List;
import java.util.Map;

public interface NewsService {

   // Map login(String userName, String password);

    Map       addNews(News news);

    Map getNewsNavList();

    Map   updateNewsByID( News  news );

    Map  getNewsList(String lan);

    Map    getNewsByID(  int  news_id);
    Map    getNewsInformationByID(  String lan,int  news_id);
}
