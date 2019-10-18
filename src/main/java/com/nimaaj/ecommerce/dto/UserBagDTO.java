package com.nimaaj.ecommerce.dto;

/**
 * Created by K550 VX on 27.09.2019.
 */
public class UserBagDTO {

    private Long id;
    private Long productId;
    private Long userId;
    private Integer count;
    private Long expireDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Long expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    @Override
    public String toString() {
        return "UserBagDTO{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", count=" + count +
                ", expireDateTime=" + expireDateTime +
                '}';
    }
}