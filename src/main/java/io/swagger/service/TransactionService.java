package io.swagger.service;

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

  private final TransactionRepo transactionRepo;
  private final AccountService accountService;
  private final UserService userService;

  @Autowired
  public TransactionService(
      TransactionRepo repo, AccountService accountService, UserService userService) {
    this.transactionRepo = repo;
    this.accountService = accountService;
    this.userService = userService;
  }

  public List<Transaction> getTransactions() {
    // validate user

    return transactionRepo.findAll();
  }

  public List<Transaction> getTransactionsPaginated(Integer offset, Integer max) {
    return transactionRepo.findAll();
  }

  public Transaction getTransactionById(UUID id) throws Exception {
    Optional<?> transaction = transactionRepo.findById(id);

    if (transaction.isPresent()) {
      return (Transaction) transaction.get();
    } else {
      throw new Exception("Transaction not found");
    }
  }

  public List<Transaction> getTransactionsByUserId() throws NotSupportedException {
    throw new NotSupportedException();
  }

  // omar
  public List<Transaction> getTransactionsByIban(String Iban) throws Exception {
    List<Transaction> allTransactions = new ArrayList<Transaction>();
    List<Transaction> filteredList = new ArrayList<Transaction>();
    allTransactions = (List<Transaction>) transactionRepo.findAll();
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

  public RegularTransaction createTransaction(RegularTransaction transaction, User user)
      throws Exception {
    if (LocalDateTime.now().minusMinutes(15).isBefore(transaction.getTransactionDate())) {
      // Transaction is too old
      throw new IllegalStateException(
          String.format("Date %s is too old to be valid", transaction.getTransactionDate()));
    }

    Account accountFrom, accountTo;
    accountFrom = accountService.getAccountByIban(transaction.getAccountFrom());
    accountTo = accountService.getAccountByIban(transaction.getAccountTo());

    if (user.getRole().equals(Role.EMPLOYEE) || user.getAccounts().contains(accountFrom)) {

      // User is transferring from their own account or is an employee

      if (accountFrom.getUserId().equals(accountTo.getUserId())) {
        return performRegularTransaction(transaction);

      } else if (accountFrom.getAccountType().equals(Account.AccountTypeEnum.CURRENT)
          && accountTo.getAccountType().equals(Account.AccountTypeEnum.CURRENT)) {
        return performRegularTransaction(transaction);
      } else {
        throw new Exception("bad stuff");
      }
    }

    throw new Exception("bad stuff");
  }

  public Deposit depositMoney(Deposit deposit) throws Exception {
    // Validate stuff

    return performDeposit(deposit);
  }

  public Withdrawal withdrawMoney(Withdrawal withdrawal) throws Exception {
    // Validate balance
    // Check Transaction Limits Limits

    return performWithdrawal(withdrawal);
  }

  // omar
  public void deleteTransactionById(UUID id) throws Exception {
    try {
      Transaction toDelete = transactionRepo.getOne(id);
      System.out.println(toDelete);
      transactionRepo.delete(toDelete);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  /*
   * Add a list of transactions. Used for test transactions
   */

  public void AddTransactions(List<Transaction> transactionList) {
    try {
      transactionRepo.saveAll(transactionList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Deposit performDeposit(Deposit deposit) {
    // Assuming valid user
    // Assuming validation done in account service
    // Assuming deposit is a valid deposit
    accountService.addBalance(deposit.getAccountTo(), deposit.getAmount());
    return transactionRepo.save(deposit);
  }

  private Withdrawal performWithdrawal(Withdrawal withdrawal) {
    // Assuming valid user
    // Assuming validation done in account
    // Assuming withdrawal is a valid withdrawal
    accountService.subtractBalance(withdrawal.getAccountFrom(), withdrawal.getAmount());
    return transactionRepo.save(withdrawal);
  }

  private RegularTransaction performRegularTransaction(RegularTransaction transaction) {
    // Assuming valid user
    // Assuming validation done in account
    // Assuming transaction is a valid transaction

    boolean deductedFrom = false, addedTo = false;

    try {
      deductedFrom =
          accountService.subtractBalance(transaction.getAccountFrom(), transaction.getAmount());
      addedTo = accountService.addBalance(transaction.getAccountTo(), transaction.getAmount());
      return transactionRepo.save(transaction);
    } catch (Exception e) {
      undoRegularTransaction(transaction, deductedFrom, addedTo);
      throw e;
    }
  }

  private void undoRegularTransaction(
      RegularTransaction transaction, boolean deductedFrom, boolean addedTo) {
    if (deductedFrom) {
      accountService.addBalance(transaction.getAccountFrom(), transaction.getAmount());
    }
    if (addedTo) {
      accountService.subtractBalance(transaction.getAccountTo(), transaction.getAmount());
    }
  }
}
