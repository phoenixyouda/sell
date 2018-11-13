package com.weitian.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018-11-08.
 */

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;

    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    @Column(name="buyer_openid")
    private String buyerOpenId;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;

}
