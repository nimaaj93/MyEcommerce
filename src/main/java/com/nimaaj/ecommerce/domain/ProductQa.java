package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_qa")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQa extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    @ManyToOne
    private User askUser;
    @ManyToOne
    private User replyUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date replyDateTime;

}
