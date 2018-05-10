package com.kst.entities;

import java.util.Date;

public class Form {
    private    int form_id;
    private  String  form_table;
    private   String from;
    private   String to;
    private   String email_title;

    public String getEmail_title() {
        return email_title;
    }

    public void setEmail_title(String email_title) {
        this.email_title = email_title;
    }

    private Date create_at;

    public String getForm_table() {
        return form_table;
    }

    public void setForm_table(String form_table) {
        this.form_table = form_table;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

}
