package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AccountTest
{
    private Account account;

    @BeforeEach
    public void Setup() {
        account = new Account("testIban", Account.AccountTypeEnum.SAVING, 400.0);
    }

    @Test
    public void testGetIban()
    {
        assertEquals("testIban", account.getIban());
    }

    @Test
    public void testSetIban()
    {
        account.setIban("newIban");
        assertEquals("newIban", account.getIban());
    }

    @Test
    public void testGetAccountType()
    {
        assertEquals(Account.AccountTypeEnum.SAVING, account.getAccountType());
    }

    @Test
    public void testSetAccountType()
    {
        account.setAccountType(Account.AccountTypeEnum.CURRENT);
        assertEquals(Account.AccountTypeEnum.CURRENT, account.getAccountType());
    }

    @Test
    public void testGetBalance()
    {
        assertTrue(account.getBalance() == 400.0);
    }

    @Test
    public void testSetBalance()
    {
        account.setBalance((double) 0);
        assertTrue(account.getBalance()== 0);
    }
}