package com.kst.entities;

import java.util.Date;

public class CCR {

    private   int ccr_id;

    private   String name;
    private   String phone;

    private   String desc;
    private Date create_at;


    private   String journey_type;
    private   String  march;

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


    public String getJourney_type() {
        return journey_type;
    }

    public void setJourney_type(String journey_type) {
        this.journey_type = journey_type;
    }

    public String getMarch() {
        return march;
    }

    public void setMarch(String march) {
        this.march = march;
    }

}
