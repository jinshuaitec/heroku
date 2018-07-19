package com.jinshuaitec.hello.task;

import com.jinshuaitec.hello.util.HttpUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author jins
 * @date on 2018/7/7.
 */
@Component
public class ScheduledTask  {

    @Autowired
    private SendEmail sendEmail;

    @Scheduled(cron = "0 20 10 * * ?")
    public void sign() throws IOException {
        int sendEmailCode=0;
        String url = "http://weixin.sztobacco.com.cn/wechat/sign/signUp?openId=oL4qWjhtXa3-pc8IbrkpS8Lj1Aas";
        JSONObject jsonObject = HttpUtil.post(url,"");
        int code = jsonObject.getInt("code");
        if (code != 200){
            sendEmailCode = -1;
        }else {
            String result = jsonObject.getString("result");
            JSONObject resultCode = new JSONObject(result);
            int returnCode =  resultCode.getInt("code");
            if ( returnCode!= 0){
                sendEmailCode=1;
            }
        }
        sendEmail.sendEmail(sendEmailCode);

    }
}
