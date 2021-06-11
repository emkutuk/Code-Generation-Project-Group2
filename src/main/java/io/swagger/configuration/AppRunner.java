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
import java.util.UUID;

@Component
@Transactional
@Log
public class AppRunner implements ApplicationRunner {
  @Autowired AccountService accountService;

  @Autowired TransactionService transactionService;

  @Autowired UserService userService;

  List<Account> accountList = new ArrayList<Account>();
  Random rnd = new Random();

  @Override
  public void run(ApplicationArguments args) throws Exception {
    /*
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

    for (Account a : accountList)
        accountService.addANewAccount(a);*/
    createTestTransactions();
  }

  private void createTestTransactions() {

    log.info("Creating test transactions");
    List<Transaction> transactionList = new ArrayList<Transaction>();
    Random rng = new Random();

    for (int i = 0; i < 10; i++) {
      transactionList.add(
          new RegularTransaction(
              "NL04INHO6868186817" + rng.nextInt(3),
              "NL01INHO0000579848" + rng.nextInt(3),
              Math.floor(rng.nextDouble() * 100),
              UUID.randomUUID()));
    }

    log.info("Creating test withdrawals and deposits");
    for (int i = 0; i < 5; i++) {
      transactionList.add(
          new Deposit(
              "NL04INHO6868186817" + rng.nextInt(3),
              Math.floor(rng.nextDouble() * 1000),
              UUID.randomUUID()));
      transactionList.add(
          new Withdrawal(
              "NL04INHO6868186817" + rng.nextInt(3),
              Math.floor(rng.nextDouble() * 1000),
              UUID.randomUUID()));
    }

    log.info("Storing test transactions");
    transactionService.AddTransactions(transactionList);
  }
}
