package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.exception.AtmCreationException;
import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.Atm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AtmService {

    public String createAtm(Atm atm) throws AtmCreationException {
        String accountNumber=null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            // insert into bank.atm values()--sql know

            accountNumber= UUID.randomUUID().toString();

            /* java know this*/ String query= " insert into bank.atm values("+
                    "'"+atm.getCardno()+"'"+","+"'"+atm.getPin()+"'"+","
                    +"'"+atm.getCvv()+"'"+","+"'"+atm.getExpiry()+"'"+","+
                    "'"+accountNumber+"'"+")";

            int status=stmt.executeUpdate(query);
            if(status==1){
                System.out.println("Account is created"+accountNumber);
            }
            else {
                throw new AtmCreationException("Accout cration failed"+atm.getCardno());
            }

        } catch (SQLException e) {
            System.out.println("SQLException"+e);
        }
        catch (AtmCreationException e){
            System.out.println("AtmCreationException"+e);
        }
        return accountNumber;
    }
}
