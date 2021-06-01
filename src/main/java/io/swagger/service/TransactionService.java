package io.swagger.service;

import io.swagger.model.Transaction;

import javax.transaction.NotSupportedException;
import java.util.List;

public class TransactionService {

  public Transaction getTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public List<Transaction> getTransactionByUserId() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public Transaction createTransaction() throws NotSupportedException {
    throw new NotSupportedException();
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
