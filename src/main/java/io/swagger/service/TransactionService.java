package io.swagger.service;

import io.swagger.model.Transaction;
import io.swagger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.NotSupportedException;
import java.util.List;

public class TransactionService {

  @Autowired
  private TransactionRepo repo;

  public List<Transaction> getTransactions() {
    return (List<Transaction>) repo.findAll();
  }


  public Transaction getTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public List<Transaction> getTransactionByUserId() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public Transaction createTransaction(Transaction transaction) {
    repo.save(transaction);
    return transaction;
  }

  public Transaction getTransactionsByIban() throws NotSupportedException {
    throw new NotSupportedException();
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

  public Transaction editTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
  }

}
