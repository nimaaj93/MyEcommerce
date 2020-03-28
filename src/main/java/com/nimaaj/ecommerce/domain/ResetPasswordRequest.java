package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reset_password_request")
@Data
@EqualsAndHashCode(callSuper = true)
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

}