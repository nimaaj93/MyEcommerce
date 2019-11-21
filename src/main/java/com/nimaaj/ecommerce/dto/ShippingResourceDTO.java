package com.nimaaj.ecommerce.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;

public class ShippingResourceDTO {

    @Null(groups = { Create.class })
    @NotNull(groups = { Update.class })
    private Long id;
    @NotNull(groups = { Create.class, Update.class })
    private Long startDateTime;
    @NotNull(groups = { Create.class, Update.class })
    private Long endDateTime;
    @NotNull(groups = { Create.class, Update.class })
    @PositiveOrZero(groups = { Create.class, Update.class })
    private Integer total;
    @NotNull(groups = { Create.class, Update.class })
    @PositiveOrZero(groups = { Create.class, Update.class })
    private Integer available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ShippingResourceDTO{" +
                "id=" + id +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", total=" + total +
                ", available=" + available +
                '}';
    }

    // validation groups
    public class Create {
    }

    public class Update {
    }

}