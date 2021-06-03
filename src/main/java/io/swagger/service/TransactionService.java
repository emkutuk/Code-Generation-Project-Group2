package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepo repo;

  @Autowired
  AccountService accountService;

  public List<Transaction> getTransactions() {
    return (List<Transaction>) repo.findAll();
  }


  public Transaction getTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public List<Transaction> getTransactionsByUserId() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public Transaction createTransaction(Transaction transaction)
  {
    repo.save(transaction);
    return transaction;
  }

  //omar
  public List<Transaction> getTransactionsByIban(String Iban) throws Exception {
    List<Transaction> allTransactions = new ArrayList<Transaction>();
    List<Transaction> filteredList = new ArrayList<Transaction>();
    allTransactions = (List<Transaction>) repo.findAll();
    try
    {
      for (Transaction t : allTransactions)
      {
        if (t.getAccountFrom().equals(Iban)) {
          filteredList.add(t);
        }
      }
      return filteredList;
    } catch (Exception e)
    {
      throw new Exception(e.getMessage());
    }
  }

  //omar
  public Transaction depositMoney(String accountTo, String accountFrom, Double amount, UUID performedBy) {
    Transaction transaction =
        new Transaction(
            UUID.randomUUID(), accountTo, accountFrom, LocalDateTime.now(), amount, performedBy);
      Account account1 = accountService.getAccountByIban(accountTo);
      Account account2 = accountService.getAccountByIban(accountFrom);
      if(account1.equals(account2)){
        //putting money through an atm, for example
        account1.setBalance(account1.getBalance() + amount);
      }
      else if (account2.getBalance() < amount) {
        //if the account you want to send from doesnt have enough balance
        return null;
      }
      else{
        //normal case
        account1.setBalance(account1.getBalance() + amount);
        account2.setBalance(account2.getBalance() - amount);
      }
    repo.save(transaction);
    return transaction;
  }

  //omar
  public Transaction withdrawMoney(String accountFrom, Double amount, UUID performedBy) {
    Transaction transaction = new Transaction(
            UUID.randomUUID(), accountFrom, accountFrom, LocalDateTime.now(), amount, performedBy);
    Account account = accountService.getAccountByIban(accountFrom);
    if (account.getBalance() < amount){
      return null;
    }
    account.setBalance(account.getBalance() - amount);
    repo.save(transaction);
    return transaction;
  }

  //omar
  public void deleteTransactionById(UUID id) throws Exception {
    try{
      Transaction toDelete = repo.getTransactionByTransactionId(id);
      System.out.println(toDelete);
      repo.delete(toDelete);
    }
    catch (Exception e){
      throw new Exception(e.getMessage());
    }
  }

  public Transaction editTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public void AddAccounts(List<Transaction> transactionList)
  {
    try{
      repo.saveAll(transactionList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
