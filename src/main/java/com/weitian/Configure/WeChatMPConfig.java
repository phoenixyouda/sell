package com.weitian.Configure;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018-11-09.
 */
@Component
public class WeChatMPConfig {
    @Autowired
    private WechatAccountConfig accountConfig;
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage( wxMpConfigStorage() );
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId( accountConfig.getAppId() );
        wxMpConfigStorage.setSecret( accountConfig.getAppSecret() );
        return wxMpConfigStorage;
    }

}
