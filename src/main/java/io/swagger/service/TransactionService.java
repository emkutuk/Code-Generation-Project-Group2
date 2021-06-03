package io.swagger.service;

import io.swagger.model.Transaction;
import io.swagger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepo repo;

  @Autowired
  private AccountService accountService;

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

  public List<Transaction> getTransactionsByIban(String Iban) {
    return repo.getTransactionsByAccountFromOrderByTransactionDate(Iban);
  }

  public Transaction depositMoney() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public Transaction withdrawMoney() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public void deleteTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public Transaction editTransactionById(UUID id,Transaction transaction)
  {
    Transaction transactionToUpdate = repo.getTransactionByTransactionId(id);
    if ( transactionToUpdate != null)
    {
      String newAccountFrom = transaction.getAccountFrom();
      String newAccountTo = transaction.getAccountTo();
      UUID newPerformedBy = transaction.getPerformedBy();
      double newAmount = transaction.getAmount();
      LocalDateTime newDate = transaction.getTransactionDate();

      if (newAccountTo != null)
        transactionToUpdate.setAccountTo(newAccountTo);
      if(newAccountFrom != null )
        transactionToUpdate.setAccountFrom(newAccountFrom);
      if(newPerformedBy != null)
        transactionToUpdate.setPerformedBy(newPerformedBy);
      if(true || validBalance(transaction))
        transactionToUpdate.setAmount(newAmount);
      if(newDate != null)
        transactionToUpdate.setTransactionDate(newDate);

      try {
        //undoTransaction(transactionToUpdate);
        //performTransaction(transaction);
        return repo.save(transactionToUpdate);
      }catch (Exception e){
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

  private boolean validAccountTypes(Transaction t) {
    // Same owner or current to current
    return true;
  }

  private boolean validBalance(Transaction t) {
    return accountService.getAccountBalanceByIban(t.getAccountFrom()) > t.getAmount();
  }

  private void performTransaction(Transaction t) {
    boolean isValidAccount = validAccountTypes(t);
    boolean isValidBalance = validBalance(t);

    if (isValidAccount && isValidBalance) {

      boolean deductedFrom = false, addedTo = false;

      try {
        // Deduct from first
        // accountService.Remove(t.getAccountFrom());
        // deductedFrom = true;

        // Add to second
        // accountService.Add(t.getAccountTo());
        // addedTo = true;

        // Write to database
        repo.save(t);

      } catch (Exception e) {
        e.printStackTrace();

        // Roll back transaction

      }
    } else {
      //if whatever isn't valid show error
    }
  }

  public Transaction undoTransaction(Transaction transaction){

    // Do stuff
    return transaction;
  }
}
