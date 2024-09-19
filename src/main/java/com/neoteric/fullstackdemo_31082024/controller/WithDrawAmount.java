package com.neoteric.fullstackdemo_31082024.controller;

import com.neoteric.fullstackdemo_31082024.exception.AtmCreationException;
import com.neoteric.fullstackdemo_31082024.model.Atm;
import com.neoteric.fullstackdemo_31082024.model.WithDraw;
import com.neoteric.fullstackdemo_31082024.service.AtmService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class WithDrawAmount {


    @PostMapping(value = "/api/withdraw",consumes = "application/json",produces = "application/json")

    public void withDrawAmount(@RequestBody WithDraw withdraw)
            throws AtmCreationException {



//        WithdrawService withdrawService=new WithdrawService();
      //  Double balance=withdrawService.withdrawAmount(withdraw);
       // withdraw.setAmount(balance);
        //return accountService.createAccount(account);
        //return withdraw;
    }

}
