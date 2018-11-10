package com.weitian;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018-11-09.
 */
@Component
@Data
@ConfigurationProperties(prefix="wechat")
public class ProjectURLConfig {
    private String projectName;
    private String domainName;
    private String turnName;

}
