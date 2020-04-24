package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends BaseEntityDto {

    private Long id;
    private String orderCode;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private List<OrderPaymentDto> payments;
    private List<OrderShippingDto> shippings;
    private InvoiceDto invoice;
    private Long userId;

}
