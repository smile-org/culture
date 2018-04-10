package com.kst.services.impl;

import com.kst.entities.*;
import com.kst.repositories.ExhibitRepo;
import com.kst.repositories.UserRepo;
import com.kst.services.ExhibitService;
import com.kst.services.UserService;
import com.kst.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExhibitRepo exhibitRepo;

    @Override
    public Map addExhibit(Exhibit exhibit) {
        Map result;
        try {

            exhibitRepo.addExhibit(exhibit);

            result = MapUtil.generateSuccessRes("添加成功");


        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getExhibitNavList() {
        Map result;
        try {
            List<NavNode> exhibitNavList = exhibitRepo.getExhibitNavList();
            result = MapUtil.generateSuccessRes(exhibitNavList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getExhibitEditPageInfoByID(Integer exhibit_id) {
        Map result;
        try {
            ExhibitEditPageInfo e = new ExhibitEditPageInfo();

            e.categoryList = new ArrayList<SelectOptionItem>();

            e.categoryList.add(new SelectOptionItem("1", "国内"));
            e.categoryList.add(new SelectOptionItem("2", "国外"));

            List<Form> lst = exhibitRepo.getFormList();
            e.formList = new ArrayList<SelectOptionItem>();

            for (Form f : lst) {
                e.formList.add(new SelectOptionItem(String.valueOf(f.getForm_id()), f.getForm_cn_name()));
            }


            if (exhibit_id != null) {
                e.exhibitToBeEdit = exhibitRepo.getExhibitByID(exhibit_id);
            }


            result = MapUtil.generateSuccessRes(e);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map updateExhibitByID(Exhibit exhibit) {

        Map result;
        try {


            exhibitRepo.updateExhibitByID(exhibit);


            result = MapUtil.generateSuccessRes("更新成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getExhibitByID(int exhibit_id) {
        Map result;
        try {
            Exhibit exhibit = exhibitRepo.getExhibitByID(exhibit_id);


            if (exhibit.getForm_id() != null) {
                Form f = exhibitRepo.getFormByID(exhibit.getForm_id());

                exhibit.setForm_cn_name(f.getForm_cn_name());
                exhibit.setForm_en_name(f.getForm_en_name());

            }
            result = MapUtil.generateSuccessRes(exhibit);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map addJJH(JJH jjh) {

        Map result;
        try {
            exhibitRepo.addJJH(jjh);

            Email email = new Email();
             Form  form = exhibitRepo.getFormByTableName("jjh");

            email.setFrom( form.getFrom());
            email.setTo( form.getTo());

             String message ="";
             message +="姓名:" + jjh.getName();
            message += "\n\r";
             message +="电话:"+ jjh.getPhone();
            message += "\n\r";
             message += "邮箱:" + jjh.getEmail();
            message += "\n\r";
             message += "咨询内容:"+ jjh.getDesc();

            email.setMessage(message);

            exhibitRepo.addEmail(email);

            result = MapUtil.generateSuccessRes("添加成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map addCCR(CCR ccr) {

        Map result;
        try {
            exhibitRepo.addCCR(ccr);

            Email email = new Email();
            Form  form = exhibitRepo.getFormByTableName("ccr");

            email.setFrom( form.getFrom());
            email.setTo( form.getTo());

            String message ="";
            message +="姓名:" + ccr.getName();
            message += "\n\r";
            message +="电话:"+ ccr.getPhone();
            message += "\n\r";

            message += "介绍:"+ ccr.getDesc();

            email.setMessage(message);

            exhibitRepo.addEmail(email);

            result = MapUtil.generateSuccessRes("添加成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    /*
    @Override
    public Map login(String userName, String password) {
        Map result;
        try {
            User user = userRepo.getUser(userName, password);

            if(user != null ) {
                result = MapUtil.generateSuccessRes(user);
            }  else {
                result = MapUtil.generateFailureRes("用户名或密码错误");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }
    */


}
