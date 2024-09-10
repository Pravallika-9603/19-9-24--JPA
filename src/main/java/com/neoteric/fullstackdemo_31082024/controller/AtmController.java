package com.neoteric.fullstackdemo_31082024.controller;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.exception.AtmCreationException;
import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.Atm;
import com.neoteric.fullstackdemo_31082024.service.AccountService;
import com.neoteric.fullstackdemo_31082024.service.AtmService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AtmController {

    @PostMapping(value = "/api/create-atm",consumes = "application/json",produces = "application/json")

    public Atm atmCreation(@RequestBody Atm atm)
            throws AtmCreationException {



        AtmService atmService=new AtmService();
        String accountNumber=atmService.createAtm(atm);
        atm.setAccountNumber(accountNumber);
        //return accountService.createAccount(account);
        return atm;
    }

}
