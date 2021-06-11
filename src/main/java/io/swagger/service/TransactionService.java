package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.model.*;
import io.swagger.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

  private final TransactionRepo repo;

  private final AccountService accountService;

  @Autowired
  public TransactionService(
      TransactionRepo repo, AccountService accountService) {
    this.repo = repo;
    this.accountService = accountService;
  }

  public List<Transaction> getTransactions() {
    // validate user

    return repo.findAll();
  }

  public List<Transaction> getTransactionsPaginated(Integer offset, Integer max) {
    return repo.findAll();
  }

  public Transaction getTransactionById(UUID id) throws Exception {
    Optional<?> transaction = repo.findById(id);

    if (transaction.isPresent()) {
      return (Transaction) transaction.get();
    } else {
      throw new Exception("Transaction not found");
    }
  }

  public List<Transaction> getTransactionsByUserId() throws NotSupportedException {
    throw new NotSupportedException();
  }

  public RegularTransaction createTransaction(RegularTransaction transaction) {
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

        // Fix
        //        if (t.getAccountFrom().equals(Iban)) {
        //          filteredList.add(t);
        //        }
      }
      return filteredList;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Deposit depositMoney(Deposit deposit) throws Exception {
    // Check transaction limits

    try{
      accountService.addBalance(deposit.getAccountTo(), deposit.getAmount());
      return repo.save(deposit);
    }catch (Exception e){
      throw new ApiException(400, "Bad Request");
    }
  }

  public Withdrawal withdrawMoney(Withdrawal withdrawal) throws Exception{
    // Validate balance
    // Check Transaction Limits Limits

    try{
      accountService.subtractBalance(withdrawal.getAccountFrom(), withdrawal.getAmount());
      return repo.save(withdrawal);
    }
    catch (Exception e)
    {
      throw new ApiException(400, "Bad Request");
    }
  }

  // omar
  public void deleteTransactionById(UUID id) throws Exception {
    try {
      Transaction toDelete = repo.getOne(id);
      System.out.println(toDelete);
      repo.delete(toDelete);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public void AddTransactions(List<Transaction> transactionList) {
    try {
      repo.saveAll(transactionList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean validAccountTypes(RegularTransaction t) throws Exception {
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
    //    if (accountService.getAccountBalanceByIban(t.getAccountFrom()) > t.getAmount()) {
    //      return true;
    //    } else {
    //      throw new Exception("Not enough to make this transaction");
    //    }
    return true;
  }

  private Transaction performTransaction(Transaction transaction) throws Exception {
    if (LocalDateTime.now().minusMinutes(15).isBefore(transaction.getTransactionDate())) {
      // Transaction is too old
      throw new IllegalArgumentException(
          String.format("Date %s is too old to be valid", transaction.getTransactionDate()));
    }

    validAccountTypes((RegularTransaction) transaction);
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

    } catch (Exception e) {
      undoTransaction(transaction, deductedFrom, addedTo);
      // throw new HttpServerErrorException.InternalServerError("There was an error performing the
      // transaction");
      e.printStackTrace();

      return null;
    }
  }

  private void undoTransaction(Transaction transaction, boolean deductedFrom, boolean addedTo) {
    if (deductedFrom) {
      // accountService.Add(transaction.getAccountFrom(), transaction.getAmount());
    }
    if (addedTo) {
      // accountService.RemoveFrom(transaction.getAccountTo(), transaction.getAmount());
    }
  }
}
