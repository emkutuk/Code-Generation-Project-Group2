package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.AccountStatus;
import io.swagger.model.AccountType;
import io.swagger.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

@Service
public class AccountService
{
    @Autowired
    private AccountRepo accountRepo;

    public void addANewAccount(Account account) throws Exception
    {
        try
        {
            if (account.getIban().equals("") || account.getIban().equals(null))
            {
                account.setIban(GenerateIban());
            }
            accountRepo.save(account);
        } catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    //TODO Authorization stuff here, I need to check user
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
        Account account = accountRepo.findByIban(iban);
        if (account != null)
            return account;
            //If there are no accounts, it returns null
        else
            return null;
    }

    // 0 Success
    // 1 Account not found
    public int changeAccountStatus(String iban, String status) throws Exception
    {
        Account account = getAccountByIban(iban);
        if (account == null)
            return 1;
        else
            {
                try
                {
                    account.setAccountStatus(AccountStatus.valueOf(status.toUpperCase(Locale.ROOT)));
                    return 0;
                } catch (Exception e)
                {
                    throw new Exception(e.getMessage());
                }
            }
        }

        public void updateAccountByIban (String iban, Account account) throws Exception {
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

        public void changeAccountType (String iban, String typeEnum) throws Exception {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();

        //Checks all accounts by their iban
        for (Account a : allAccounts)
        {
            if (a.getIban().equals(iban))
            {
                try
                {
                    accountRepo.delete(a);
                    a.setAccountType(AccountType.valueOf(typeEnum.toUpperCase(Locale.ROOT)));
                    accountRepo.save(a);
                } catch (Exception e)
                {
                    throw new Exception(e.getMessage());
                }
            }
        }
    }

        public Double getAccountBalanceByIban (String iban)
        {
            List<Account> allAccounts = (List<Account>) accountRepo.findAll();

            //Checks all accounts by their iban
            for (Account a : allAccounts)
            {
                if (a.getIban().equals(iban)) return a.getBalance();
            }
            return null;
        }

        public boolean addBalance (String iban,double amount) throws Exception {
        try
        {
            Account account = getAccountByIban(iban);
            double balance = account.getBalance();

            //Checks if amount is valid
            if (amount > 0)
            {
                account.setBalance(balance + amount);
                return true;
            } else
            {
                throw new Exception("Amount needs to be more than 0.");
            }
        } catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

        public boolean subtractBalance (String iban,double amount) throws Exception {
        try
        {
            Account account = getAccountByIban(iban);
            double balance = account.getBalance();

            //Checks if amount is valid
            if (amount > 0 && balance >= amount)
            {
                account.setBalance(balance - amount);
                return true;
            } else
            {
                throw new Exception("Amount needs to be more than 0 or balance needs to be more than the amount.");
            }
        } catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

        public String GenerateIban () {
        //Format : NLxxINHO0xxxxxxxxx
        // 2 digits - 9 digits

        //Check what is the last iban in db
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();
        String lastIban = allAccounts.get((int) allAccounts.stream().count() - 1).getIban();

        //First check the last 9 digits to see if they are not all 9
        String last9Digits = lastIban.substring(lastIban.length() - 9);
        String first2Digits = lastIban.substring(2, 4);

        if (last9Digits != "999999999")
        {
            last9Digits = String.format("%09d", (parseInt(last9Digits) + 1));
        }
        //If they are all 9es then first 2 digits needs to increase
        else
        {
            last9Digits = String.format("%09d", 0);
            first2Digits = String.format("%02d", (parseInt(first2Digits) + 1));
        }

        //Combine all iban together and return the value
        return "NL" + first2Digits + "INHO0" + last9Digits;
    }
    }
