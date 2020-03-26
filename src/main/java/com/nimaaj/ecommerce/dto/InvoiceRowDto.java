package com.nimaaj.ecommerce.dto;

public class InvoiceRowDto {

    private Long id;
    private String title;
    private String decription;
    private Long amount;
    private OrderItemDto orderItem;
    private Long invoiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public OrderItemDto getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItemDto orderItem) {
        this.orderItem = orderItem;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
