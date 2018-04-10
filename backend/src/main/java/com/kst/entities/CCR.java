package com.kst.entities;

import java.util.Date;

public class CCR {

    public   int ccr_id;


    public   String name;
    public   String phone;

    public   String desc;
    public Date create_at;

    public int getCcr_id() {
        return ccr_id;
    }

    public void setCcr_id(int ccr_id) {
        this.ccr_id = ccr_id;
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
