package com.kst.entities;

import java.util.Date;

public class JJH {
    private   int jjh_id;
    private   String name;
    private   String phone;


    private   String email;
    private   String desc;
    private Date create_at;

    private  String   company;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
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
