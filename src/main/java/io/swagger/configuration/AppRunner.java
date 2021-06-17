package io.swagger.configuration;

import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.TransactionService;
import io.swagger.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Transactional
@Log
public class AppRunner implements ApplicationRunner {
  @Autowired AccountService accountService;

  @Autowired UserService userService;

  @Autowired TransactionService transactionService;

  List<Account> accountList = new ArrayList<Account>();
  Random rnd = new Random();

  @Override
  public void run(ApplicationArguments args) throws Exception {

    // Creating the account for the bank
    Account bankAccount = new Account("NL01INHO0000000001", AccountType.CURRENT, 0.0);
    accountService.addANewAccount(bankAccount);

    Account bankAccount2 = new Account();

    for (int i = 0; i < 390; i++) {
      if (rnd.nextBoolean())
        accountList.add(new Account(AccountType.SAVING, (double) rnd.nextInt(500000)));
      else accountList.add(new Account(AccountType.CURRENT, (double) rnd.nextInt(500000)));
    }

    for (Account a : accountList) accountService.addANewAccount(a);

    // Creating users
    List<User> usersList = new ArrayList<User>();
    // Customers
    usersList.add(
        new User(
            "Hein",
            "Eken",
            "31685032148",
            "customer",
            "customer",
            null,
            io.swagger.security.Role.ROLE_CUSTOMER,
            AccountStatus.ACTIVE));

    // Employees
    usersList.add(
        new User(
            "Amst",
            "Erdam",
            "31685032149",
            "employee",
            "employee",
            null,
            io.swagger.security.Role.ROLE_EMPLOYEE,
            AccountStatus.ACTIVE));

    for (User u : usersList) userService.register(u);

    log.info("Testing transaction");
    testTransaction();

    log.info("The application has started successfully.");
  }

  private void testTransaction() {
    User testUser1 =
        new User(
            "Test",
            "User1",
            "whocareslmao",
            "test",
            "test",
            new ArrayList<>(),
            io.swagger.security.Role.ROLE_EMPLOYEE,
            AccountStatus.ACTIVE);

    Account testAccount1 = new Account("NL03INHO0000009000", AccountType.CURRENT, 100d);
    testUser1.getAccounts().add(testAccount1);

    User testUser2 =
        new User(
            "Test",
            "User2",
            "whocareslmao",
            "test",
            "test",
            new ArrayList<>(),
            io.swagger.security.Role.ROLE_EMPLOYEE,
            AccountStatus.ACTIVE);
    Account testAccount2 = new Account("NL03INHO0000009001", AccountType.CURRENT, 100d);
    testUser2.getAccounts().add(testAccount2);

    RegularTransaction testTransaction =
        new RegularTransaction(
            "NL03INHO0000009000", "NL03INHO0000009001", 20.00, testUser2.getId());

    try {
      accountService.addANewAccount(testAccount1);
      accountService.addANewAccount(testAccount2);
      userService.createUser(testUser1);
      userService.createUser(testUser2);
      transactionService.createTransaction(testTransaction, testUser2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
