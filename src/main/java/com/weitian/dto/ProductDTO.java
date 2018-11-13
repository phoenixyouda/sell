package com.weitian.dto;

import com.weitian.enums.ProductStatusEnum;
import com.weitian.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018-11-13.
 */
@Data
public class ProductDTO {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Integer CategoryType;
    private Date createTime;
    private Date updateTime;

    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode( this.productStatus,ProductStatusEnum.class );
    }
}
