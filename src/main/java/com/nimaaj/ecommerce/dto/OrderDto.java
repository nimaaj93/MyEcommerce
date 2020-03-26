package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.OrderStatus;

import java.util.List;

public class OrderDto extends BaseEntityDto {

    private Long id;
    private String orderCode;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private List<OrderPaymentDto> payments;
    private List<OrderShippingDto> shippings;
    private InvoiceDto invoice;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public List<OrderPaymentDto> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPaymentDto> payments) {
        this.payments = payments;
    }

    public List<OrderShippingDto> getShippings() {
        return shippings;
    }

    public void setShippings(List<OrderShippingDto> shippings) {
        this.shippings = shippings;
    }

    public InvoiceDto getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDto invoice) {
        this.invoice = invoice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
