package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_bag")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    private Integer count;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDateTime;

}
