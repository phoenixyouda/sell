package com.weitian.Configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018-11-09.
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatAccountConfig {
    private String appId;
    private String appSecret;
}
