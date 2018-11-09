package com.weitian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2018-11-08.
 */
@Data
@AllArgsConstructor
public class CartDTO {
    private String productId;
    private Integer quantity;
}
