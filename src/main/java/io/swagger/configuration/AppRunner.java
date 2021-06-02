package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.service.AccountService;
import io.swagger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class AppRunner implements ApplicationRunner {
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
    // transactionService.getTransactions().forEach(System.out::println);
  }

  private void createTestTransactions() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // From DB
    Transaction transaction1 = new Transaction(
            UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),
            "NL04INHO6818968668",
            "NL01INHO0000579848",
            LocalDateTime.parse("2021-12-01 16:02:06", formatter),
            20.5,
            UUID.fromString("43368da9-36bb-4cae-bf12-1169360e4ac4"));

    // New Transaction Now
    Transaction transaction2 = new Transaction(
            "NL04INHO6868186817",
            "NL01INHO0000579848",
            1500.00,
            UUID.randomUUID()
    );

    // Future transaction
    Transaction transaction3 = new Transaction(
            "NL09INHO6891486186",
            "NL01INHO0000579848",
            LocalDateTime.parse("2021-12-01 16:02:06", formatter),
            1700.00,
            transaction1.getPerformedBy()
    );

    List<Transaction> transactionList = new ArrayList<Transaction>();
    transactionList.add(transaction1);
    transactionList.add(transaction2);
    transactionList.add(transaction3);

    transactionService.AddAccounts(transactionList);

  }
}
