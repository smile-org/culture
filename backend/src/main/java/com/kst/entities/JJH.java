package com.kst.entities;

import java.util.Date;

public class JJH {
    public   int jjh_id;
    public   String name;
    public   String phone;


    public   String email;
    public   String desc;
    public Date create_at;

    public int getJjh_id() {
        return jjh_id;
    }

    public void setJjh_id(int jjh_id) {
        this.jjh_id = jjh_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

}
