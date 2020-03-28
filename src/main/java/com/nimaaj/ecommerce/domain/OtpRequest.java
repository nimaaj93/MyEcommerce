package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "otp_request")
@Data
@EqualsAndHashCode(callSuper = true)
public class OtpRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otpCode;
    @ManyToOne
    private User user;
    private boolean used;

}