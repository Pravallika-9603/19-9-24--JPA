package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo_31082024.model.Account;
import com.neoteric.fullstackdemo_31082024.model.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AccountService {
    public String createAccountUsingHibernate(Account account){
        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());
        accountEntity.setPan(account.getPan());
        accountEntity.setName(account.getName());
        session.persist(accountEntity);
        transaction.commit();
        return  accountEntity.getAccountNumber();

    }
    public String createAccount(Account account) throws AccountCreationFailedException{
        String accountNumber=null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
           // insert into bank.atm values()--sql know

            accountNumber= UUID.randomUUID().toString();
/* java know this*/ String query= " insert into bank.account values("+
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
        return accountNumber;
    }
}
