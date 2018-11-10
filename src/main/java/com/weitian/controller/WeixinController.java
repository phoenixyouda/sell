package com.weitian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018-11-09.
 */
@RestController
//@RequestMapping("/wechat")
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/authorize")
    public String authorize(@RequestParam("code") String code, @RequestParam("state") String state){
        //手动获取openid方法：
        //第一步：https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46e410ef8d421013&redirect_uri=http://n9i9ta.natappfree.cc/sell/wechat/authorize&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect



        log.info("进入author....");
        log.info( "code:{}",code );
        log.info( "state:{}",state );

        //第二步
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx46e410ef8d421013&secret=cbf90ea5b6313f6987e12a37a32df4eb&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate(  );
        String response=restTemplate.getForObject( url,String.class );
        return response;

    }
}
