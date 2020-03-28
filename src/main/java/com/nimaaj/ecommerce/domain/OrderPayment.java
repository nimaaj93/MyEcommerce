package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.PaymentMethod;
import com.nimaaj.ecommerce.enumaration.PaymentProvider;
import com.nimaaj.ecommerce.enumaration.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_payment",
        uniqueConstraints = @UniqueConstraint(name = "payment_requestid_uq",
                                              columnNames = "requestId"))
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPayment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @ManyToOne
    private Order order;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    private PaymentProvider provider;
    private String requestId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDateTime;
    @ManyToOne
    private User user;

}
