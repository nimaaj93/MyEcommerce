package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.DiscountCodeType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount_code")
public class DiscountCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEn;
    private String titleFa;
    private String description;
    @Enumerated(EnumType.STRING)
    private DiscountCodeType discountCodeType;
    private Long maxAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
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
}
