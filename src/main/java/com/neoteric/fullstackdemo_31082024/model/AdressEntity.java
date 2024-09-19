package com.neoteric.fullstackdemo_31082024.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address",schema = "bank")
@Data
public class AdressEntity {
    public AdressEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer id;

    @Column(name = "state")
    public String state;



    @ManyToOne
    @JoinColumn(name = "adharno",referencedColumnName = "adharno")
    public AdharEntity adharEntity;
}
