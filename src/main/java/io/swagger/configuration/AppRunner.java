package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.service.AccountService;
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

    List<Account> accountList = new ArrayList<Account>();
    Random rnd = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        //Creating the account for the bank
        Account bankAccount = new Account(1L, "NL01INHO0000000001", Account.AccountTypeEnum.CURRENT, 0.0);
        accountService.addANewAccount(bankAccount);

        for(int i = 0; i < 390 ; i++)
        {
            if(rnd.nextBoolean())
                accountList.add(new Account(Account.AccountTypeEnum.SAVING, (double) rnd.nextInt(500000)));
            else
                accountList.add(new Account(Account.AccountTypeEnum.CURRENT, (double) rnd.nextInt(500000)));
        }

        for (Account a : accountList){
            accountService.addANewAccount(a);
            System.out.printf(a.toString());
        }
    }
}
