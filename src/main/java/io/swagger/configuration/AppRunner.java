package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AppRunner implements ApplicationRunner
{
    @Autowired
    AccountService accountService;

    Account account = new Account("testIBAN", Account.AccountTypeEnum.SAVING, (double) 500);

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        accountService.addANewAccount(account);

        for(Account a : accountService.getAllAccounts())
        {
            System.out.println(a.toString());
        }

    }
}
