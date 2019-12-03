package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.enumaration.DiscountCodeType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class DiscountCodeDTO {

    @NotNull(groups = { Update.class })
    @Null(groups = { Create.class })
    private Long id;
    @NotBlank(groups = { Create.class, Update.class })
    private String title;
    @Null(groups = { Update.class })
    private String codeVal;
    private String description;
    @NotNull(groups = { Create.class, Update.class })
    private DiscountCodeType discountCodeType;
    private String discountRefVal;
    private Long maxAmount;
    private Long expireDateTime;
    @NotNull(groups = { Create.class })
    @Null(groups = { Update.class })
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

    public Long getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Long expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public DiscountCodeState getState() {
        return state;
    }

    public void setState(DiscountCodeState state) {
        this.state = state;
    }

    // validation groups
    public class Create {
    }

    public class Update {
    }

    @Override
    public String toString() {
        return "DiscountCodeDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", codeVal='" + codeVal + '\'' +
                ", description='" + description + '\'' +
                ", discountCodeType=" + discountCodeType +
                ", discountRefVal='" + discountRefVal + '\'' +
                ", maxAmount=" + maxAmount +
                ", expireDateTime=" + expireDateTime +
                ", state=" + state +
                '}';
    }
}