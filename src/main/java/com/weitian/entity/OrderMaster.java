package com.weitian.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018-11-08.
 */

@Entity
@Data
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

}
