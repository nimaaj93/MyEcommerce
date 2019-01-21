package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.SaleType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by K550 VX on 1/19/2019.
 */
@Entity
@Table(name = "sale")
public class Sale extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SaleType saleType;
    private String saleRefVal;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDateTime;

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

    public Date getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Date expireDateTime) {
        this.expireDateTime = expireDateTime;
    }
}
