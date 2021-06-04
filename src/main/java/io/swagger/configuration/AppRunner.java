package io.swagger.configuration;

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
  private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
  @Autowired
  AccountService accountService;
  Account account = new Account("testIBAN", Account.AccountTypeEnum.SAVING, (double) 500);
  @Autowired
  TransactionService transactionService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    accountService.addANewAccount(account);

    for (Account a : accountService.getAllAccounts()) {
      System.out.println(a.toString());
    }

    createTestTransactions();
  }

  private void createTestTransactions() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    log.info("Creating test transactions");
    List<Transaction> transactionList = new ArrayList<Transaction>();
    Random rng = new Random();

    for (int i = 0; i < 100; i++) {
      transactionList.add(new Transaction(
              "NL04INHO6868186817" + rng.nextInt(5),
              "NL01INHO0000579848" + rng.nextInt(5),
              Math.floor(rng.nextDouble() * 100),
              UUID.randomUUID()
      ));
    }

    log.info("Storing test transactions");
    transactionService.AddTransactions(transactionList);

  }
}
