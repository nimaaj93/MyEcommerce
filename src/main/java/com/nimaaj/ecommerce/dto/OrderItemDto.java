package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.OrderItemStatus;

import java.util.List;

public class OrderItemDto {

    private Long id;
    private OrderItemStatus status;
    private ProductDto product;
    private Integer orderCount;
    private List<SaleDto> sales;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public List<SaleDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDto> sales) {
        this.sales = sales;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
