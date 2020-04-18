package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductQaStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(max = 511)
    @NotNull
    private String question;

    @Size(max = 1023)
    private String answer;

    @NotNull
    private ProductQaStatus status;

    @ManyToOne
    @NotNull
    private User askUser;

    @ManyToOne
    private User replyUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date replyDateTime;

    @ManyToOne
    @NotNull
    private Product product;

}
