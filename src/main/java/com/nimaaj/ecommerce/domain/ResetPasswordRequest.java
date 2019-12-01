package com.nimaaj.ecommerce.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reset_password_request")
public class ResetPasswordRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String requestCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDateTime;
    private boolean consumed;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Date getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Date expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
}