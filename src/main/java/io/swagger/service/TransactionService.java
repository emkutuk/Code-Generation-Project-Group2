package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repo.TransactionRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log
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
    if (LocalDateTime.now().minusMinutes(3).isAfter(transaction.getTransactionDate())) {
      // Transaction is too old
      log.info("Transaction is too old");
      throw new IllegalStateException(
          String.format("Date %s is too old to be valid", transaction.getTransactionDate()));
    }

    User userFrom = userService.getUserByIban(transaction.getAccountFrom());
    Account accountFrom = accountService.getAccountByIban(transaction.getAccountFrom());
    Account accountTo = accountService.getAccountByIban(transaction.getAccountTo());

    if (userFrom == null || accountFrom == null || accountTo == null) {
      log.info("User or account doesn't exist");
      throw new IllegalArgumentException("one of these doesn't exist");
    }

    // If user is a customer and does not own the account from which the money is leaving
    if (user.getRole().equals(Role.CUSTOMER) && !userFrom.getAccounts().contains(accountFrom)) {
      log.info("User trying to make transaction from another users account");
      throw new IllegalArgumentException("user is not authorized to do this");
    }
    // If user does not own the account to and it is a savings account
    else if (!(userFrom.getAccounts().contains(accountTo))
        && accountTo.getAccountType().equals(Account.AccountTypeEnum.SAVING)) {
      log.info("User trying to make transaction to savings account of another user");
      throw new IllegalArgumentException("Cannot transfer to savings of another user");
    }
    log.info("Checked role and account owner");

    return performRegularTransaction(transaction);
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

    log.info("Performing transaction");

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
