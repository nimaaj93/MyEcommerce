package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authentication")
@Data
@EqualsAndHashCode(callSuper = true)
public class Authentication extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String salt;

    @OneToOne
    @NotNull
    private User user;

}
