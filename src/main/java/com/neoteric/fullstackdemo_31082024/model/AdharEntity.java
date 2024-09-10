package com.neoteric.fullstackdemo_31082024.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "adhar",schema = "bank")
@Data
public class AdharEntity {

    public AdharEntity(){

    }
    @Id
    @Column(name="adharno")
    public Integer adharno;

    @Column(name="name")
    public String name;

    @OneToMany
    public List<AdressEntity> adressEntities;

}
