package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.model.Account;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.AccountType;
import io.swagger.model.LoginDto;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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
    private AccountService accountService;

    private Account account;
    private String customerToken;
    private String employeeToken;

    @BeforeEach
    public void setup() throws Exception
    {
        account = new Account("testIban", AccountType.SAVING, 15.0);
        ObjectMapper mapper = new ObjectMapper();

        LoginDto customer = new LoginDto("customer", "customer");
        LoginDto employee = new LoginDto("employee", "employee");

        customerToken = this.mvc.perform(post("/Login").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(customer))).andReturn().getResponse().getContentAsString();
        employeeToken = this.mvc.perform(post("/Login").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(employee))).andReturn().getResponse().getContentAsString();

    }

    @Test
    public void testAddANewAccount() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/Accounts").header("authorization", "Bearer " + employeeToken).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(account))).andExpect(status().isCreated());
    }

    @Test
    public void testChangeAccountType() throws Exception
    {
        accountService.addANewAccount(account);
        this.mvc.perform(post("/Accounts/testIban/current").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk());
    }

    @Test
    public void testChangeAccountStatus() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(post("/Accounts/testIban/Status/closed").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk());
    }

    @Test
    public void testGetAccountBalanceByIban() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(get("/Accounts/testIban/Balance").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk());
    }

    @Test
    public void testGetAccountBalanceByWrongIban() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(get("/Accounts/wrongIban/Balance").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk());
    }

    @Test
    public void testGetAccountByIban() throws Exception
    {
        accountService.addANewAccount(this.account);
        this.mvc.perform(get("/Accounts/testIban").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk());
    }

    @Test
    public void testGetAllAccounts() throws Exception
    {
        given(accountService.getAllAccounts()).willReturn(Collections.singletonList(account));
        this.mvc.perform(get("/Accounts?offset=0&max=10").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk()).andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
    public void testUpdateAccountByIban() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        accountService.addANewAccount(this.account);
        this.mvc.perform(put("/Accounts/testIban").header("authorization", "Bearer " + employeeToken).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(account))).andExpect(status().isOk());
    }
}