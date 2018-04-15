package com.kst.repositories;

import com.kst.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExhibitRepo {

   // User getUser(@Param("userName") String userName, @Param("password") String password);

    void   addExhibit(Exhibit  exhibit);

    List<NavNode>   getExhibitNavList();

    List<Form> getFormList();

    Form  getFormByID(int form_id);

    Exhibit    getExhibitByID(int  exhibit_id);


    void   updateExhibitByID( Exhibit   exhibit);

    void   addJJH( JJH jjh);

    void  addCCR(  CCR  ccr);

    void  addEmail(  Email  email);

    Form  getFormByTableName(String  form_table);

    List<Exhibit>   getExhibitFrontNavList(  int  category);

    List<Email>   getEmailList();

    void   updateEmail(  Email   email);
}
