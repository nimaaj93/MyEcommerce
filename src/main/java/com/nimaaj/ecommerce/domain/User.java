package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.Gender;
import com.nimaaj.ecommerce.enumaration.UserType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "app_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @OneToOne
    private Authentication authentication;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "user")
    private List<UserBag> userBagList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<UserBag> getUserBagList() {
        return userBagList;
    }

    public void setUserBagList(List<UserBag> userBagList) {
        this.userBagList = userBagList;
    }
}
