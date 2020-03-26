package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.enumaration.SaleType;


public class SaleDto {

    private Long id;
    private SaleType saleType;
    private String saleRefVal;
    private SaleStatus saleStatus;
    private Long startDateTime;
    private Long endDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleType getSaleType() {
        return saleType;
    }

    public void setSaleType(SaleType saleType) {
        this.saleType = saleType;
    }

    public String getSaleRefVal() {
        return saleRefVal;
    }

    public void setSaleRefVal(String saleRefVal) {
        this.saleRefVal = saleRefVal;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Long getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Long endDateTime) {
        this.endDateTime = endDateTime;
    }
}
