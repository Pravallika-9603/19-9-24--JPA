package com.neoteric.fullstackdemo_31082024.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "account",schema = "bank")
@Data
public class AccountEntity {

    public AccountEntity(){

    }

    @Column(name = "name",nullable = false)
    private String name;

    @Id      //primarykey at table level
    @Column(name = "accountnumber",nullable = false)  //nullable--for notnull
    private String accountNumber;

    @Column(name = "pan",nullable = false)
    private String pan;

    @Column(name = "mobile",nullable = false)
    private String mobileNumber;

    @Column(name = "balance",nullable = false)
    private double balance;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "accountEntity",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    public List<AccountAddressEntity> accountAddressEntityList;
}