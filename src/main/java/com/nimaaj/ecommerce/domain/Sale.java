package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.enumaration.SaleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by K550 VX on 1/19/2019.
 */
@Entity
@Table(name = "sale")
@Data
@EqualsAndHashCode(callSuper = true)
public class Sale extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    private String saleRefVal;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

}
