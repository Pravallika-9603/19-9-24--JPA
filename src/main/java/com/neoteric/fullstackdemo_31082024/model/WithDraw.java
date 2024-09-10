package com.neoteric.fullstackdemo_31082024.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WithDraw {

    private String cardno;
    private String pin;
    private Double amount;

}
