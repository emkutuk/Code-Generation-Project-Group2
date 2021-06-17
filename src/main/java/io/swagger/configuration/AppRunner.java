package io.swagger.configuration;

import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Component
public class AppRunner implements ApplicationRunner
{
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    List<Account> accountList = new ArrayList<Account>();
    Random rnd = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        //Creating the account for the bank
        Account bankAccount = new Account("NL01INHO0000000001", AccountType.CURRENT, 0.0);
        accountService.addANewAccount(bankAccount);
        List<Account> accounts = new ArrayList<Account>();
        for(int i = 0; i < 20 ; i++)
        {
            if(rnd.nextBoolean())
                accounts.add(new Account(AccountType.SAVING, (double) rnd.nextInt(500000)));
            else
                accounts.add(new Account(AccountType.CURRENT, (double) rnd.nextInt(500000)));
        }
        //for(Account a: accounts)
        //    accountService.addANewAccount(a);

        //Creating users
        List<User> usersList= new ArrayList<User>();
        //Customers
        usersList.add(new User("Customer", "Kutuk", "31685032148", "customer", "customer",accounts ,io.swagger.security.Role.ROLE_CUSTOMER, AccountStatus.ACTIVE));

        //Employees
        usersList.add(new User("Employee", "Cinarli", "31685032148", "employee", "employee", accounts,io.swagger.security.Role.ROLE_EMPLOYEE, AccountStatus.ACTIVE));

        for(User u : usersList)
            userService.register(u.getEmail(), u.getPassword(), u.getRole());


        System.out.println("The application has started successfully.");
    }

}

