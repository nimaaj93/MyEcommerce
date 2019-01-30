package com.nimaaj.ecommerce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "app_user_authority")
public class Authority extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String authorityVal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityVal() {
        return authorityVal;
    }

    public void setAuthorityVal(String authorityVal) {
        this.authorityVal = authorityVal;
    }
}
