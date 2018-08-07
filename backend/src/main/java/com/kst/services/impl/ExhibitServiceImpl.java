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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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

            //List<Form> lst = exhibitRepo.getFormList();
           // e.formList = new ArrayList<SelectOptionItem>();

           // for (Form f : lst) {
           //     e.formList.add(new SelectOptionItem(String.valueOf(f.getForm_id()), f.getForm_cn_name()));
           // }


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
    public Map getExhibitByID(  String lan, int exhibit_id) {
        Map result;
        try {
            Exhibit exhibit = exhibitRepo.getExhibitByID(exhibit_id);


            /*
            if (exhibit.getForm_id() != null) {
                Form f = exhibitRepo.getFormByID(exhibit.getForm_id());

                exhibit.setForm_cn_name(f.getForm_cn_name());
                exhibit.setForm_en_name(f.getForm_en_name());

            }
            */

            if(lan.equals("cn"))
            {
                exhibit.setTitle_en(null);
                exhibit.setContent_en(null);
            }
            else
            {
                exhibit.setTitle_cn(null);
                exhibit.setContent_cn(null);
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
            message += "单位:" + jjh.getCompany();
            message += "\n\r";
             message += "城市:" + jjh.getEmail();
            message += "\n\r";
             message += "咨询内容:"+ jjh.getDesc();

            email.setMessage(message);
            email.setTitle(form.getEmail_title());
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
            message += "项目:" + ccr.getJourney_type();
            message += "\n\r";
            message += "行程:" + ccr.getMarch();
            message += "\n\r";
            message +="姓名:" + ccr.getName();
            message += "\n\r";

            message +="联系电话:"+ ccr.getPhone();
            message += "\n\r";

            message += "备注说明:"+ ccr.getDesc();

            email.setMessage(message);
            email.setTitle(form.getEmail_title());
            exhibitRepo.addEmail(email);

            result = MapUtil.generateSuccessRes("添加成功");

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }

    @Override
    public Map getExhibitFrontNavList(String  lan) {

        Map result;
        try {

            ExhibitFrontNav   nav =new  ExhibitFrontNav();
            nav.internalList=   exhibitRepo.getExhibitFrontNavList(1);

            for( Exhibit  e :    nav.internalList)
            {
                if(lan.equals("cn"))
                {
                     e.setTitle_en(null);
                }
                else
                {
                    e.setTitle_cn(null);
                }
            }

            nav.internationalList=   exhibitRepo.getExhibitFrontNavList(2);
            for( Exhibit  e :    nav.internationalList)
            {
                if(lan.equals("cn"))
                {
                    e.setTitle_en(null);
                }
                else
                {
                    e.setTitle_cn(null);
                }
            }



            result = MapUtil.generateSuccessRes(nav);

        } catch (Exception e) {
            logger.error(e.getMessage());
            result = MapUtil.generateFailureRes(e.getMessage());
        }
        return result;
    }



    @Value("${email.server}")
    private String emailserver;
    @Value("${email.port}")
    private String emailport;
    @Value("${email.from}")
    private String emailfrom;
    @Value("${email.pwd}")
    private String emailpwd;
    @Value("${email.retrycount}")
    private String emailretrycount;

    @Override
    public void sendEmail() {

        List<Email>   emailList= exhibitRepo.getEmailList();
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        senderImpl.setHost(emailserver);
        senderImpl.setPort(  Integer.parseInt(emailport) );

        senderImpl.setUsername( emailfrom);//用户名
        senderImpl.setPassword(emailpwd);//密码

         for(Email email :emailList)
         {
             Email update =new  Email();

             try {
                 SimpleMailMessage message = new SimpleMailMessage();//消息构造器
                 message.setFrom(emailfrom);//发件人

                  String[] to =  email.getTo().split(";");


                 message.setTo( to );//收件人
                 message.setSubject(   email.getTitle());//主题
                 message.setText( email.getMessage());//正文
                 senderImpl.send(message);

                 update.setStatus(1);
                 update.setEmail_id(  email.getEmail_id());
                 update.setRetry_count( email.getRetry_count() +1);
                 exhibitRepo.updateEmail(update);
             }

             catch (Exception ex)
             {
                 update.setEmail_id(  email.getEmail_id());
                 //update.setStatus(0);
                 update.setRetry_count( email.getRetry_count() +1);
                 exhibitRepo.updateEmail(update);

                 logger.error(ex.getMessage());
             }
         }

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
