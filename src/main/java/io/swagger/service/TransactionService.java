package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepo repo;

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
  public Transaction depositMoney() throws NotSupportedException {
    throw new NotSupportedException();
  }

  //omar
  public Transaction withdrawMoney() throws NotSupportedException {
    throw new NotSupportedException();
  }

  //omar
  public void deleteTransactionById() throws NotSupportedException {
    throw new NotSupportedException();
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
