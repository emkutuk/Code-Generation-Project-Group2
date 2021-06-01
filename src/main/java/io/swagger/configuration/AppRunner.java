package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class AppRunner implements ApplicationRunner
{
    @Autowired
    AccountService accountService;

    List<Account> accountList = new ArrayList<Account>();

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        accountList = Arrays.asList(new Account("NL03INHO0033576852", Account.AccountTypeEnum.SAVING, (double) 500), new Account("NL03INHO0033577452", Account.AccountTypeEnum.CURRENT, (double) 489), new Account("NL03INHO0038886852", Account.AccountTypeEnum.SAVING, (double) 120000), new Account("NL03INHO0033276852", Account.AccountTypeEnum.CURRENT, (double) 112), new Account("NL03INHO0033576666", Account.AccountTypeEnum.SAVING, (double) 132), new Account("NL03INHO0033585752", Account.AccountTypeEnum.CURRENT, (double) 125), new Account("NL03INHO0033856852", Account.AccountTypeEnum.SAVING, (double) 1999), new Account("NL03INHO0021576852", Account.AccountTypeEnum.CURRENT, 1117.01), new Account("NL03INHO0033576869", Account.AccountTypeEnum.SAVING, 12007.99), new Account("NL03INHO0028576852", Account.AccountTypeEnum.SAVING, (double) 17785), new Account("NL03INHO0033576442", Account.AccountTypeEnum.SAVING, (double) 1));

        for (Account a : accountList)
            accountService.addANewAccount(a);
    }
}
