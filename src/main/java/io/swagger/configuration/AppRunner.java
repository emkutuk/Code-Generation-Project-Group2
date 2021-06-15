package io.swagger.configuration;

import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
@Transactional
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

        for(int i = 0; i < 390 ; i++)
        {
            if(rnd.nextBoolean())
                accountList.add(new Account(AccountType.SAVING, (double) rnd.nextInt(500000)));
            else
                accountList.add(new Account(AccountType.CURRENT, (double) rnd.nextInt(500000)));
        }

        for (Account a : accountList)
            accountService.addANewAccount(a);

        //Creating users
        List<User> usersList= new ArrayList<User>();
        //Customers
        usersList.add(new User("Emre", "Kutuk", "31685032148", "emkutuk@gmail.com", "emre", null, io.swagger.security.Role.ROLE_CUSTOMER, AccountStatus.ACTIVE));

        //Employees
        usersList.add(new User("Ege", "Cinarli", "31685032148", "egecinarli@gmail.com", "ege", null, io.swagger.security.Role.ROLE_EMPLOYEE, AccountStatus.ACTIVE));

        for(User u : usersList)
            userService.register(u.getEmail(), u.getPassword(), u.getRole());


        System.out.println("The application has started successfully.");
    }

}

