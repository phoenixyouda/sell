package com.weitian.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weitian.entity.OrderDetail;
import com.weitian.enums.CodeEnum;
import com.weitian.enums.OrderStatusEnum;
import com.weitian.enums.PayStatusEnum;
import com.weitian.utils.EnumUtil;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Code;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018-11-08.
 */
@Data
@DynamicUpdate
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenId;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode( orderStatus, OrderStatusEnum.class );
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode( payStatus, PayStatusEnum.class );
    }
}
