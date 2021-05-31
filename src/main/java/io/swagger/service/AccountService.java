package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService
{
    @Autowired
    AccountRepo accountRepo;

    public void createAccount(Account account)
    {
        accountRepo.save(account);
    }

    public List<Account> getAllAccounts()
    {
        return (List<Account>)accountRepo.findAll();
    }
}
