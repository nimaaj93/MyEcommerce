package com.nimaaj.ecommerce.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Order order;

    private Long totalItemsAmount;
    private Long otherItemsAmount;
    private Long totalAmount;
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceRow> rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public List<InvoiceRow> getRows() {
        return rows;
    }

    public void setRows(List<InvoiceRow> rows) {
        this.rows = rows;
    }
}
