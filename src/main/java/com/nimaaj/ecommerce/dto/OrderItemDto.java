package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.OrderItemStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderItemDto {

    private Long id;
    private OrderItemStatus status;
    private ProductDto product;
    private Integer orderCount;
    private List<SaleDto> sales;
    private Long orderId;

}
