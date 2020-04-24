package com.nimaaj.ecommerce.dto;

import lombok.Data;

@Data
public class InvoiceRowDto {

    private Long id;
    private String title;
    private String decription;
    private Long amount;
    private OrderItemDto orderItem;
    private Long invoiceId;

}
