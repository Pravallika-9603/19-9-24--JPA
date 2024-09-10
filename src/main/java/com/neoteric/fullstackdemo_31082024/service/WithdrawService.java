package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.WithDraw;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

//public class WithdrawService {

   /* public void withdrawAmount(WithDraw withdraw) {
        String balance = null;
        try {
            Connection connection = DBConnection.getConnection();
            //   Statement stmt = connection.createStatement();
            // insert into bank.atm values()--sql know
 

            /* java know thisString query= " insert into bank.account values("+
                    "'"+accountNumber+"'"+","+
                    "'"+account.getName()+"'"+","+
                    "'"+account.getPan()+"'"+","+
                    "'"+account.getMobileNumber()+"'"+","
                    +account.getBalance()+")";
            int status=stmt.executeUpdate(query);
            if(status==1){
                System.out.println("Account is created"+accountNumber);
            }
            else {
                throw new AccountCreationFailedException("Accout cration failed"+account.getPan());
            }

        } catch (SQLException e) {
            System.out.println("SQLException"+e);
        }
        catch (AccountCreationFailedException e){
            System.out.println("AccountCreationFailedException"+e);
        }
        return accountNumber;*/
     //   }

  ///}
