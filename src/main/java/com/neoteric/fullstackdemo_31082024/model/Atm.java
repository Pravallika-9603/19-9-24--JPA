package com.neoteric.fullstackdemo_31082024.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Atm {
    private String cardno;
    private String pin;
    private String accountNumber;
    private String cvv;
    private String expiry;
}
