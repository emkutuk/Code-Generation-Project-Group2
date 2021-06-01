package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AccountService
{
    @Autowired
    AccountRepo accountRepo;

    public void addANewAccount(Account account) throws Exception
    {
        try
        {
            accountRepo.save(account);
        } catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Account> getAllAccounts() throws Exception
    {
        try
        {
            return (List<Account>) accountRepo.findAll();
        } catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public Account getAccountByIban(String iban)
    {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();

        //Checks all accounts by their iban
        for (Account a : allAccounts)
        {
            if (a.getIban().equals(iban)) return a;
        }
        //If there are no accounts, it returns null
        return null;
    }

    public void deleteAccountByIban(String iban) throws Exception
    {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();

        //Checks all accounts by their iban
        for (Account a : allAccounts)
        {
            if (a.getIban().equals(iban))
            {
                try
                {
                    accountRepo.delete(a);
                } catch (Exception e)
                {
                    throw new Exception(e.getMessage());
                }
            }
        }
    }

    public void updateAccountByIban(String iban, Account account) throws Exception
    {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();

        //Checks all accounts by their iban
        for (Account a : allAccounts)
        {
            if (a.getIban().equals(iban))
            {
                try
                {
                    a.setIban(account.getIban());
                    a.setAccountType(account.getAccountType());
                    a.setBalance(account.getBalance());
                    accountRepo.save(a);
                } catch (Exception e)
                {
                    throw new Exception(e.getMessage());
                }
            }
        }
    }

    public void changeAccountType(String iban, String typeEnum) throws Exception
    {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();

        //Checks all accounts by their iban
        for (Account a : allAccounts)
        {
            if (a.getIban().equals(iban))
            {
                try
                {
                    accountRepo.delete(a);
                    a.setAccountType(Account.AccountTypeEnum.valueOf(typeEnum.toUpperCase(Locale.ROOT)));
                    accountRepo.save(a);
                } catch (Exception e)
                {
                    throw new Exception(e.getMessage());
                }
            }
        }
    }

    public Double getAccountBalanceByIban(String iban)
    {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();

        //Checks all accounts by their iban
        for (Account a : allAccounts)
        {
            if (a.getIban().equals(iban)) return a.getBalance();
        }
        return null;
    }
}
