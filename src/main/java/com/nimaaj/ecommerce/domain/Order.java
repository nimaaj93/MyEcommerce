package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "order_all")
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @OneToMany(mappedBy = "order")
    private List<OrderPayment> payments;

    @OneToMany(mappedBy = "order")
    private List<OrderShipping> shippings;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    @ManyToOne
    @NotNull
    private User user;

}
