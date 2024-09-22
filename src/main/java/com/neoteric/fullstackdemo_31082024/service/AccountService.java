package com.neoteric.fullstackdemo_31082024.service;

import com.neoteric.fullstackdemo_31082024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31082024.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo_31082024.model.*;
import com.neoteric.fullstackdemo_31082024.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Service(value = "accountServiceTest")
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account searchAccountByManagedJpa(String accountnumber){
        Account account=null;
        Optional<AccountEntity> optionalAccountEntity=accountRepository.findById(accountnumber);
        if(optionalAccountEntity.isPresent()) {
            AccountEntity accountEntity = optionalAccountEntity.get();
            account = Account.builder()
                    .accountNumber(accountEntity.getAccountNumber())
                    .name(accountEntity.getName())
                    .mobileNumber(accountEntity.getMobileNumber())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .balance(accountEntity.getBalance())
                    .build();


            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();


        if (Objects.nonNull(accountAddressEntityList)&& accountAddressEntityList.size()>0) {

            AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
            System.out.println("AccountAddressEntity is Loaded");
            Address address = new Address();
            address.setAdd1(accountAddressEntity.getAddress1());
            address.setAdd2(accountAddressEntity.getAddress2());
            address.setPincode(accountAddressEntity.getPincode());
            address.setCity(accountAddressEntity.getCity());
            address.setState(accountAddressEntity.getState());
            account.setAddress(address);
        }

        }
        System.out.println(account);
        return account;

    }

    public String oneToMany1(Account account){
        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

        AdharEntity adharEntity=new AdharEntity();
        adharEntity.setName(account.getName());

       List<AdressEntity> addressEntitList=new ArrayList<>();
        AdressEntity adressEntity=new AdressEntity();
        adressEntity.setState(account.getMobileNumber());
        adressEntity.setAdharEntity(adharEntity);
        addressEntitList.add(adressEntity);
        adharEntity.setAdressEntities(addressEntitList);

        return "";

    }
    public String createAccount(Account account) throws AccountCreationFailedException{
        String accountNumber=null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            accountNumber= UUID.randomUUID().toString();
            String query= " insert into bank.account values("+
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
    public  String oneTOManyUsingHibernate(Account account){
        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();

        Transaction transaction=session.beginTransaction();
        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setName(account.getName());
        accountEntity.setMobileNumber(account.getMobileNumber());
        accountEntity.setPan(account.getPan());


        List<AccountAddressEntity> accountAddressEntityList=new ArrayList<>();
        AccountAddressEntity accountAddressEntity=new AccountAddressEntity();

        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);
        accountAddressEntityList.add(accountAddressEntity);
        accountEntity.setAccountAddressEntityList(accountAddressEntityList);
        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();
    }





    public Account accountSearch(String accNo){

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<AccountEntity> query = session.createQuery("From AccountEntity a where a.accountnumber=:inputAccountNumber");
        query.setParameter("inputAccountNumber", accNo);
        AccountEntity accountEntity = query.list().get(0);
        System.out.println("Account is Loaded");

        Account account = Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .name(accountEntity.getName())
                .mobileNumber(accountEntity.getMobileNumber())
                .balance(accountEntity.getBalance())
                .pan(accountEntity.getPan())
                .balance(accountEntity.getBalance())
                .build();

        List<AccountAddressEntity> accountAddressEntityList=
                accountEntity.getAccountAddressEntityList();
        AccountAddressEntity accountAddressEntity=accountAddressEntityList.get(0);
        System.out.println("AccountAddressEntity is Loaded");
        Address address=new Address();
        address.setAdd1(accountAddressEntity.getAddress1());
        address.setAdd2(accountAddressEntity.getAddress2());
        address.setPincode(accountAddressEntity.getPincode());
        address.setCity(accountAddressEntity.getCity());
        address.setState(accountAddressEntity.getState());
        account.setAddress(address);

        System.out.println("Address is Loaded");

        return account;
    }

    public Account jpa(String accNo){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaDemo");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        jakarta.persistence.Query query= entityManager.createQuery("select a from AccountEntity a where a.accountNumber=:inputAccountNumber ");
        query.setParameter("inputAccountNumber",accNo);

        List<AccountEntity> accountEntities = query.getResultList();
        AccountEntity accountEntity=accountEntities.get(0);
        Account account= Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .mobileNumber(accountEntity.getMobileNumber())
                .pan(accountEntity.getPan())
                .balance(accountEntity.getBalance())
                .name(accountEntity.getName())
                .build();
        List<AccountAddressEntity> accountAddressEntityList=
                accountEntity.getAccountAddressEntityList();

        if (Objects.nonNull(accountAddressEntityList)&& accountAddressEntityList.size()>0){

            AccountAddressEntity accountAddressEntity=accountAddressEntityList.get(0);
            System.out.println("AccountAddressEntity is Loaded");
            Address address=new Address();
            address.setAdd1(accountAddressEntity.getAddress1());
            address.setAdd2(accountAddressEntity.getAddress2());
            address.setPincode(accountAddressEntity.getPincode());
            address.setCity(accountAddressEntity.getCity());
            address.setState(accountAddressEntity.getState());
            account.setAddress(address);


        }
        entityManager.getTransaction().commit();
        return account;

    }

    public String createAccountUsingHibernate (Account account){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());
        List<AccountAddressEntity> accountAddressEntityList=new ArrayList<>();
        AccountAddressEntity accountAddressEntity=new AccountAddressEntity();
        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);
        accountAddressEntityList.add(accountAddressEntity);
        accountEntity.setAccountAddressEntityList(accountAddressEntityList);
        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();

    }


    public String createAccUsingJpa(Account account) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaDemo");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());

        List<AccountAddressEntity> accountAddressEntityList = new ArrayList<>();
        AccountAddressEntity accountAddressEntity = new AccountAddressEntity();
        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);
        accountAddressEntityList.add(accountAddressEntity);
        accountEntity.setAccountAddressEntityList(accountAddressEntityList);
        entityManager.persist(accountEntity);
        entityManager.getTransaction().commit();
        return accountEntity.getAccountNumber();
    }
}

