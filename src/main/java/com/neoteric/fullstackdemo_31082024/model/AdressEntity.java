package com.neoteric.fullstackdemo_31082024.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address",schema = "bank")
@Data
public class AdressEntity {
    public AdressEntity(){}

    @Id
    @Column(name = "adharno")
    public Integer id;

    @Column(name = "state")
    public String state;

    @ManyToOne
    @JoinColumn(name = "id")
    public AdharEntity adharEntity;
}
