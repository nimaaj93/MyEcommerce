package com.nimaaj.ecommerce.dto;

import java.util.List;

public class InvoiceDto {

    private Long id;
    private Long orderId;
    private Long totalItemsAmount;
    private Long otherItemsAmount;
    private Long totalAmount;
    private List<InvoiceRowDto> rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTotalItemsAmount() {
        return totalItemsAmount;
    }

    public void setTotalItemsAmount(Long totalItemsAmount) {
        this.totalItemsAmount = totalItemsAmount;
    }

    public Long getOtherItemsAmount() {
        return otherItemsAmount;
    }

    public void setOtherItemsAmount(Long otherItemsAmount) {
        this.otherItemsAmount = otherItemsAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<InvoiceRowDto> getRows() {
        return rows;
    }

    public void setRows(List<InvoiceRowDto> rows) {
        this.rows = rows;
    }
}
