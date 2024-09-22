package com.neoteric.fullstackdemo_31082024.controller;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class AccountController {

    @Autowired
    AccountService accountServiceTest;

//    @PostMapping(value="/api/createAccount",
//            consumes = "application/json",
//            produces = "application/json")
//    public Account getaccountNumberUsingHibernate(@RequestBody Account account)
//            throws Exception {
//        AccountService accountService=new AccountService();
//        String accountNumber=accountService.createAccUsingJpa(account);
//        account.setAccountNumber(accountNumber);
//        return account;
//    }


    @GetMapping(value = "/api/searchAccount", consumes = "application/json", produces = "application/json")

    public Account accountSearch(@RequestHeader("accountnumber") String accountnumber) {

       // AccountService accountService=new AccountService();
        return accountServiceTest.searchAccountByManagedJpa(accountnumber);
    }

}