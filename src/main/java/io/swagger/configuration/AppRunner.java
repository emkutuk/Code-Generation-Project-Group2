package io.swagger.configuration;

import io.swagger.api.TransactionsApiController;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.service.AccountService;
import io.swagger.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Transactional
public class AppRunner implements ApplicationRunner {
  @Autowired AccountService accountService;

  @Autowired TransactionService transactionService;

  private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

  List<Account> accountList = new ArrayList<Account>();
  Random rnd = new Random();

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // Creating the account for the bank
    Account bankAccount =
        new Account(1L, "NL01INHO0000000001", Account.AccountTypeEnum.CURRENT, 0.0);
    accountService.addANewAccount(bankAccount);

    for (int i = 0; i < 390; i++) {
      if (rnd.nextBoolean())
        accountList.add(new Account(Account.AccountTypeEnum.SAVING, (double) rnd.nextInt(500000)));
      else
        accountList.add(new Account(Account.AccountTypeEnum.CURRENT, (double) rnd.nextInt(500000)));
    }
    for (Account a : accountList) accountService.addANewAccount(a);
    createTestTransactions();
  }

  private void createTestTransactions() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    log.info("Creating test transactions");
    List<Transaction> transactionList = new ArrayList<Transaction>();
    Random rng = new Random();

    for (int i = 0; i < 100; i++) {
      transactionList.add(
          new Transaction(
              "NL04INHO6868186817" + rng.nextInt(5),
              "NL01INHO0000579848" + rng.nextInt(5),
              Math.floor(rng.nextDouble() * 100),
              UUID.randomUUID()));
    }

    log.info("Storing test transactions");
    transactionService.AddTransactions(transactionList);
  }
}
