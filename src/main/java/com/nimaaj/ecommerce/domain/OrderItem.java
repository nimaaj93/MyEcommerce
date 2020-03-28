package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.OrderItemStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by K550 VX on 1/19/2019.
 */
@Entity
@Table(name = "order_item")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    @OneToOne
    private Product product;

    private Integer beforeStock;

    private Integer currentStock;

    private Integer orderCount;

    @OneToMany
    private List<Sale> sales;

    @ManyToOne
    private Order order;

}
