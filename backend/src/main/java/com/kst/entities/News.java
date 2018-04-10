package com.kst.entities;

import java.util.Date;

public class News {

    public  int news_id;
    public  String image;

    public  String title_cn;
    public  String title_en;
    public  String link;

    public  String desc_cn;
    public  String desc_en;

    public  String content_cn;
    public  String content_en;
    public Date create_at;

    public Date update_at;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle_cn() {
        return title_cn;
    }

    public void setTitle_cn(String title_cn) {
        this.title_cn = title_cn;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc_cn() {
        return desc_cn;
    }

    public void setDesc_cn(String desc_cn) {
        this.desc_cn = desc_cn;
    }

    public String getDesc_en() {
        return desc_en;
    }

    public void setDesc_en(String desc_en) {
        this.desc_en = desc_en;
    }

    public String getContent_cn() {
        return content_cn;
    }

    public void setContent_cn(String content_cn) {
        this.content_cn = content_cn;
    }

    public String getContent_en() {
        return content_en;
    }

    public void setContent_en(String content_en) {
        this.content_en = content_en;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }


}
