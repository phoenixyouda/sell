package com.weitian.controller;

import com.weitian.ProjectURLConfig;
import com.weitian.enums.ResultEnum;
import com.weitian.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by Administrator on 2018-11-09.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private ProjectURLConfig projectURLConfig;
    @RequestMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")  String returnUrl){

        /*1、客户端请求/wechat/authorize后，进入本方法,开始回调wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode( returnUrl )
                方法，并重定向到方法返回值RedirectUrl，用户微信界面会显示授权等待界面，用户点击授权后，进入参数url中，同时传入code
        2、在url（这里设置为/wechar/userInfo）中，根据用户code获取token，并从token中获取用户信息及openId，获取完成后，wxMpService.oauth2buildAuthorizationUrl方法回调完毕
        3、方法回调完毕后，向客户端返回returnUrl*/


        String url=projectURLConfig.getDomainName()+projectURLConfig.getProjectName()+projectURLConfig.getTurnName();
        String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode( returnUrl ));
        log.info( "redirectUrl:"+redirectUrl );
        return "redirect:"+redirectUrl;

    }
    @RequestMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){

        try {
            log.info( "returnUrl:{}",returnUrl );
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            String openId=wxMpUser.getOpenId();
            returnUrl=returnUrl+"?openid="+openId;
        } catch (WxErrorException e) {
            log.error( "【微信授权失败】,{}", e);
            new SellException( ResultEnum.WECHAT_MP_ERROR.getCode(),e.getMessage() );
        }
        return returnUrl;
    }
}
