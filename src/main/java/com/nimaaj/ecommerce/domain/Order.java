package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_all")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderCode;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
    @OneToMany(mappedBy = "order")
    private List<OrderPayment> payments;
    @OneToMany(mappedBy = "order")
    private List<OrderShipping> shippings;
    @OneToOne(mappedBy = "order")
    private Invoice invoice;

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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPayment> payments) {
        this.payments = payments;
    }

    public List<OrderShipping> getShippings() {
        return shippings;
    }

    public void setShippings(List<OrderShipping> shippings) {
        this.shippings = shippings;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
