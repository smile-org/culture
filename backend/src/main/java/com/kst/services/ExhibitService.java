package com.kst.services;

import com.kst.entities.CCR;
import com.kst.entities.Exhibit;
import com.kst.entities.JJH;
import com.kst.entities.NavNode;

import java.util.List;
import java.util.Map;

public interface ExhibitService {

    //Map login(String userName, String password);

    Map   addExhibit(Exhibit exhibit);

    Map getExhibitNavList();

    Map   getExhibitEditPageInfoByID(Integer  exhibit_id);


    Map      updateExhibitByID( Exhibit   exhibit);


    Map    getExhibitByID( String lan, int  exhibit_id);


    Map   addJJH( JJH jjh);

    Map  addCCR(  CCR ccr);


    Map    getExhibitFrontNavList(String lan);

    void  sendEmail();
}
