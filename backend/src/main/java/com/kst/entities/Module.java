package com.kst.entities;

import java.util.Date;

public class Module {
    private  int  module_id;


    private  String  image_cn;
    private  String  image_en;
    private  String  link_cn;
    private  String  link_en;

    private  String  title_cn;
    private  String  title_en;
    private  String  desc_cn;

    private  String  desc_en;
    private Date create_at;
    private  Date  update_at;


    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getImage_cn() {
        return image_cn;
    }

    public void setImage_cn(String image_cn) {
        this.image_cn = image_cn;
    }

    public String getImage_en() {
        return image_en;
    }

    public void setImage_en(String image_en) {
        this.image_en = image_en;
    }

    public String getLink_cn() {
        return link_cn;
    }

    public void setLink_cn(String link_cn) {
        this.link_cn = link_cn;
    }

    public String getLink_en() {
        return link_en;
    }

    public void setLink_en(String link_en) {
        this.link_en = link_en;
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
