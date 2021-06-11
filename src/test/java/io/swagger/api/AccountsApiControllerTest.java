package io.swagger.api;

import io.swagger.model.Account;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountsApiControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private Account account;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    public void setup()
    {
        account = new Account("testIban", Account.AccountTypeEnum.SAVING, 15.0);
    }

    @Test
    public void testAddANewAccount() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/Accounts").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(account))).andExpect(status().isCreated());
    }

    @Test
    public void testChangeAccountType() throws Exception
    {
        accountService.addANewAccount(account);
        this.mvc.perform(post("/Accounts/testIban/current")).andExpect(status().isOk());
    }

    @Test
    public void testChangeAccountStatus() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(post("/Accounts/testIban/Status/closed")).andExpect(status().isOk());
   }

    @Test
    public void testGetAccountBalanceByIban() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(get("/Accounts/testIban/Balance")).andExpect(status().isOk());
    }

    @Test
    public void testGetAccountByIban() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(get("/Accounts/testIban")).andExpect(status().isOk());
    }

    @Test
    public void testGetAllAccounts() throws Exception
    {
        given(accountService.getAllAccounts()).willReturn(Collections.singletonList(account));
        this.mvc.perform(get("/Accounts?offset=0&max=10")).andExpect(status().isOk()).andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
    public void testUpdateAccountByIban() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        accountService.addANewAccount(this.account);
        this.mvc.perform(put("/Accounts/testIban").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(account))).andExpect(status().isOk());
    }
}