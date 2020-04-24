package com.nimaaj.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {

    private Long id;
    private Long orderId;
    private Long totalItemsAmount;
    private Long otherItemsAmount;
    private Long totalAmount;
    private List<InvoiceRowDto> rows;

}
