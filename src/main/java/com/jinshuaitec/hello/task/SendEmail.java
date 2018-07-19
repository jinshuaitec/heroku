package com.jinshuaitec.hello.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author jins
 * @date on 2018/7/7.
 */
@Service
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(int code){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("767957644@qq.com");
        simpleMailMessage.setTo("767957644@qq.com");
        simpleMailMessage.setSubject("主题:微信烟草签到");
        if (code == 0){
            simpleMailMessage.setText("微信签到成功!");
        }else {
            simpleMailMessage.setText("微信签到失败!");
        }
        try{
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.println("发送邮件失败!"+e.getMessage());
        }

     }
}
