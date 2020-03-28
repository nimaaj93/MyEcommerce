package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.Gender;
import com.nimaaj.ecommerce.enumaration.UserState;
import com.nimaaj.ecommerce.enumaration.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "app_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobileNumber;
    private String nationalCode;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @OneToOne(mappedBy = "user")
    private Authentication authentication;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "user")
    private List<UserBag> userBagList;
    @ManyToMany
    private Set<Authority> authorities;
    private boolean activated;
    @Enumerated(EnumType.STRING)
    private UserState userState;

}