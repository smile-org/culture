package com.kst.schedulers;


import com.kst.services.ExhibitService;
import com.kst.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ExhibitService exhibitService;

    private   static   boolean isRunning=false;



    //第一次延迟1秒执行，当执行完后1分钟再执行
    @Scheduled(initialDelay = 1000, fixedDelay = 6000*10 )
    public void testSchedulerJob() {
        if(isRunning)  return;
        isRunning=true;

        try {
            exhibitService.sendEmail();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
         isRunning=false;
    }

}
