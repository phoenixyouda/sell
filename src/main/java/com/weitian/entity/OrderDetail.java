package com.weitian.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018-11-08.
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private String productIcon;
    private Integer productQuantity;
}
