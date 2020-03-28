package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.enumaration.DiscountCodeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "discount_code")
@Data
@EqualsAndHashCode(callSuper = true)
public class DiscountCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String codeVal;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DiscountCodeType discountCodeType;

    private String discountRefVal;

    private Long maxAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDateTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DiscountCodeState state;

}
