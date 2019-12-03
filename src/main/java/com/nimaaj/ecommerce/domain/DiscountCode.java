package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.enumaration.DiscountCodeType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount_code")
public class DiscountCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String codeVal;
    private String description;
    @Enumerated(EnumType.STRING)
    private DiscountCodeType discountCodeType;
    private String discountRefVal;
    private Long maxAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDateTime;
    @Enumerated(EnumType.STRING)
    private DiscountCodeState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCodeVal() {
        return codeVal;
    }

    public void setCodeVal(String codeVal) {
        this.codeVal = codeVal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiscountCodeType getDiscountCodeType() {
        return discountCodeType;
    }

    public void setDiscountCodeType(DiscountCodeType discountCodeType) {
        this.discountCodeType = discountCodeType;
    }

    public String getDiscountRefVal() {
        return discountRefVal;
    }

    public void setDiscountRefVal(String discountRefVal) {
        this.discountRefVal = discountRefVal;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Date getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Date expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public DiscountCodeState getState() {
        return state;
    }

    public void setState(DiscountCodeState state) {
        this.state = state;
    }
}
