package com.kst.repositories;

import com.kst.entities.NavNode;
import com.kst.entities.News;
import com.kst.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsRepo {

   // User getUser(@Param("userName") String userName, @Param("password") String password);

    void   addNews(News  news);

    List<NavNode> getNewsNavList();

    void   updateNewsByID( News  news );

    List<News>  getNewsList();

    News  getNewsByID(int  news_id);
}
