package com.kst.entities;

public class SelectOptionItem {
    private   String value;
    private  String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


     public    SelectOptionItem(   String v,  String t       )
     {
         this. value= v;
         this.text= t;
     }
}
