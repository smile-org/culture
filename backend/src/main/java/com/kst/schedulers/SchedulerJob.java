package com.kst.schedulers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //第一次延迟1秒执行，当执行完后3分钟再执行
    @Scheduled(initialDelay = 1000, fixedDelay = 3000 * 60)
    public void testSchedulerJob() {
        try {
            logger.info("this is a testing scheduler job.");
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

    }

}
