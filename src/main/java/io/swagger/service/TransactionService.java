package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

  @Autowired private TransactionRepo repo;

  @Autowired private AccountService accountService;

  public List<Transaction> getTransactions() {
    // validate user

    return (List<Transaction>) repo.findAll();
  }

  public List<Transaction> getTransactionsPaginated(Integer offset, Integer max) {
    return (List<Transaction>) repo.findAll();
  }

  public Transaction getTransactionById(UUID id) {
    return repo.getTransactionByTransactionId(id);
  }

  public List<Transaction> getTransactionsByUserId() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public Transaction createTransaction(Transaction transaction) {
    // performTransaction(transaction);

    return repo.save(transaction);
  }

  // omar
  public List<Transaction> getTransactionsByIban(String Iban) throws Exception {
    List<Transaction> allTransactions = new ArrayList<Transaction>();
    List<Transaction> filteredList = new ArrayList<Transaction>();
    allTransactions = (List<Transaction>) repo.findAll();
    try {
      for (Transaction t : allTransactions) {
        if (t.getAccountFrom().equals(Iban)) {
          filteredList.add(t);
        }
      }
      return filteredList;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  // omar
  public Transaction depositMoney(
      String accountTo, String accountFrom, Double amount, UUID performedBy) {
    Transaction transaction =
        new Transaction(
            UUID.randomUUID(), accountTo, accountFrom, LocalDateTime.now(), amount, performedBy);
    Account account1 = accountService.getAccountByIban(accountTo);
    Account account2 = accountService.getAccountByIban(accountFrom);
    if (account1.equals(account2)) {
      // putting money through an atm, for example
      account1.setBalance(account1.getBalance() + amount);
    } else if (account2.getBalance() < amount) {
      // if the account you want to send from doesnt have enough balance
      return null;
    } else {
      // normal case
      account1.setBalance(account1.getBalance() + amount);
      account2.setBalance(account2.getBalance() - amount);
    }
    repo.save(transaction);
    return transaction;
  }

  // omar
  public Transaction withdrawMoney(String accountFrom, Double amount, UUID performedBy) {
    Transaction transaction =
        new Transaction(
            UUID.randomUUID(), accountFrom, accountFrom, LocalDateTime.now(), amount, performedBy);
    Account account = accountService.getAccountByIban(accountFrom);
    if (account.getBalance() < amount) {
      return null;
    }
    account.setBalance(account.getBalance() - amount);
    repo.save(transaction);
    return transaction;
  }

  // omar
  public void deleteTransactionById(UUID id) throws Exception {
    try {
      Transaction toDelete = repo.getTransactionByTransactionId(id);
      System.out.println(toDelete);
      repo.delete(toDelete);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Transaction editTransactionById(UUID id, Transaction transaction) throws Exception{
    Transaction transactionToUpdate = repo.getTransactionByTransactionId(id);
    if (transactionToUpdate != null) {
      String newAccountFrom = transaction.getAccountFrom();
      String newAccountTo = transaction.getAccountTo();
      UUID newPerformedBy = transaction.getPerformedBy();
      double newAmount = transaction.getAmount();
      LocalDateTime newDate = transaction.getTransactionDate();

      if (newAccountTo != null) transactionToUpdate.setAccountTo(newAccountTo);
      if (newAccountFrom != null) transactionToUpdate.setAccountFrom(newAccountFrom);
      if (newPerformedBy != null) transactionToUpdate.setPerformedBy(newPerformedBy);
      if (true || validBalance(transaction)) transactionToUpdate.setAmount(newAmount);
      if (newDate != null) transactionToUpdate.setTransactionDate(newDate);

      try {
        // undoTransaction(transactionToUpdate);
        // performTransaction(transaction);
        return repo.save(transactionToUpdate);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public void AddTransactions(List<Transaction> transactionList) {
    try {
      repo.saveAll(transactionList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean validAccountTypes(Transaction t) throws Exception {
    Account accountFrom, accountTo;
    accountFrom = accountService.getAccountByIban(t.getAccountFrom());
    accountTo = accountService.getAccountByIban(t.getAccountTo());

    // If user is transferring between own accounts or directly to another Current Account
    // transaction is valid

    if (accountFrom.getAccountType().equals(Account.AccountTypeEnum.CURRENT)
        && accountTo.getAccountType().equals(Account.AccountTypeEnum.CURRENT)) {
      return true;
    }
//    else if (accountFrom.getUser().equals(accountTo.getUser())) {
//      return true;
//    }
    throw new Exception("Account types are not valid");
  }

  private boolean validBalance(Transaction t) throws Exception {
    if (accountService.getAccountBalanceByIban(t.getAccountFrom()) > t.getAmount()) {
      return true;
    } else {
      throw new Exception("Not enough to make this transaction");
    }
  }

  private Transaction performTransaction(Transaction transaction) throws Exception {
    if (LocalDateTime.now().minusMinutes(15).isBefore(transaction.getTransactionDate())) {
      // Transaction is too old
      throw new IllegalArgumentException(
          String.format("Date %s is too old to be valid", transaction.getTransactionDate()));
    }

    validAccountTypes(transaction);
    validBalance(transaction);
    boolean deductedFrom = false, addedTo = false;

    try {
      /*
       // Deduct from first
       accountService.Remove(t.getAccountFrom(), transaction.getAmount());
       deductedFrom = true;

       // Add to second
       accountService.Add(t.getAccountTo(), transaction.getAmount());
       addedTo = true;
       Write to database
      */

//      accountService.updateTransactions(transaction.getAccountTo());
//      accountService.updateTransactions(transaction.getAccountFrom());

      return repo.save(transaction);


    } catch (Exception e)
    {
      undoTransaction(transaction, deductedFrom, addedTo);
      // throw new HttpServerErrorException.InternalServerError("There was an error performing the transaction");
      e.printStackTrace();

      return null;
    }
  }

  private void undoTransaction(Transaction transaction, boolean deductedFrom, boolean addedTo)
  {
    if(deductedFrom){
      // accountService.Add(transaction.getAccountFrom(), transaction.getAmount());
    }
    if(addedTo){
      // accountService.RemoveFrom(transaction.getAccountTo(), transaction.getAmount());
    }
  }
}
