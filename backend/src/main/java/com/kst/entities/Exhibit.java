package com.kst.entities;

import java.util.Date;

public class Exhibit {


    private  int exhibit_id;
    private  String title_cn;
    private  String title_en;
    private  String content_cn;
    private  String content_en;

    private  int category;
    private  Integer form_id;
    private  String  form_en_name;
    private   String   form_cn_name;



    private  int status;
    private Date create_at;
    private Date update_at;

    public String getForm_en_name() {
        return form_en_name;
    }

    public void setForm_en_name(String form_en_name) {
        this.form_en_name = form_en_name;
    }

    public String getForm_cn_name() {
        return form_cn_name;
    }

    public void setForm_cn_name(String form_cn_name) {
        this.form_cn_name = form_cn_name;
    }


    public int getExhibit_id() {
        return exhibit_id;
    }

    public void setExhibit_id(int exhibit_id) {
        this.exhibit_id = exhibit_id;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Integer getForm_id() {
        return form_id;
    }

    public void setForm_id(Integer form_id) {
        this.form_id = form_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
