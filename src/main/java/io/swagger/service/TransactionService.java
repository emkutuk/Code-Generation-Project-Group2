package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repo.TransactionRepo;
import io.swagger.security.Role;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log
@NoArgsConstructor
public class TransactionService
{

    private TransactionRepo transactionRepo;
    private AccountService accountService;
    private UserService userService;

    @Autowired
    public TransactionService(TransactionRepo repo, AccountService accountService, UserService userService)
    {
        this.transactionRepo = repo;
        this.accountService = accountService;
        this.userService = userService;
    }

    //omar
    public List<Transaction> getTransactions()
    {
        // validate user
        return transactionRepo.findAll();
    }

    //omar
    public Transaction getTransactionById(String id) throws Exception
    {
        //validate user
        String s = id.replace("-", "");
        UUID uuid = new UUID(new BigInteger(s.substring(0, 16), 16).longValue(), new BigInteger(s.substring(16), 16).longValue());
        Optional<?> transaction = transactionRepo.findById(uuid);
        if (transaction.isPresent())
        {
            return (Transaction) transaction.get();
        } else
        {
            throw new Exception("Transaction not found");
        }
    }

    //omar
    public List<Transaction> getTransactionsByUserId(UUID id, Integer max, Integer offset) throws Exception
    {
        User user = userService.getUserById(id);
        try
        {
            List<Transaction> allTransactions = new ArrayList<Transaction>();
            List<Transaction> filteredList = new ArrayList<>();
            List<Transaction> accountTransactions;
            List<Account> userAccounts = user.getAccounts();

            for (Account a : userAccounts)
            {
                accountTransactions = getAllTransactionsForAccountByIban(a.getIban());
                if (!accountTransactions.isEmpty())
                {
                    allTransactions.addAll(accountTransactions);
                }
            }
            long maxValue = max + offset;
            //If the maxValue is bigger then existing transactions, max value is equal to allTransactions count
            if (maxValue > allTransactions.stream().count()) maxValue = allTransactions.stream().count();

            for (int i = offset; i < maxValue; i++)
                filteredList.add(allTransactions.get(i));

            return filteredList;
        } catch (Exception e)
        {
            throw new Exception("Invalid User ID or no transactions found");
        }
    }

    // omar
    public List<Transaction> getTransactionsByIban(String Iban, Integer max, Integer offset) throws Exception
    {
        //validate user
        List<Transaction> allAccountTransactions = getAllTransactionsForAccountByIban(Iban);
        ArrayList<Transaction> filteredList = new ArrayList<Transaction>();
        try
        {
            for (int i = offset; i <= allAccountTransactions.size(); i++)
            {
                filteredList.add(allAccountTransactions.get(i));
                if (filteredList.size() == max)
                {
                    break;
                }
            }
        } catch (Exception e)
        {
            throw new Exception("Transaction not found");
        }
        return filteredList;
    }

    // omar
    @Transient
    public void deleteTransactionById(String id) throws Exception
    {
        //validate user
        try
        {
            //Converting String to UUID (Using UUID.toString(id) causes an illegal argument exception
            String s = id.replace("-", "");
            UUID uuid = new UUID(new BigInteger(s.substring(0, 16), 16).longValue(), new BigInteger(s.substring(16), 16).longValue());
            transactionRepo.deleteById(uuid);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Something went wrong");
            throw new Exception("Unable to delete transaction");
        }
    }

  public RegularTransaction createTransaction(RegularTransaction transaction, User user) throws Exception {
    isValidTransactionDate(transaction);
    Account accountFrom = accountService.getAccountByIban(transaction.getAccountFrom());
    User userFrom = userService.getUserByIban(accountFrom);
    Account accountTo = accountService.getAccountByIban(transaction.getAccountTo());

    if (userFrom == null || accountFrom == null || accountTo == null) {
      log.info("User or account doesn't exist");
      throw new IllegalArgumentException("one of these doesn't exist");
    }

    // If user is a customer and does not own the account from which the money is leaving
    if (!userFrom.getId().equals(user.getId()) && user.getRole().equals(Role.ROLE_CUSTOMER)) {
      log.info("User trying to make transaction from another users account");
      throw new IllegalArgumentException("user is not authorized to do this");
    }
    else if (!(userFrom.getAccounts().contains(accountTo))
        && accountTo.getAccountType().equals(AccountType.SAVING)) {
      log.info("User trying to make transaction to savings account of another user");
      throw new IllegalArgumentException("Cannot transfer to savings of another user");
    }
    log.info("Checked role and account owner");

    return performRegularTransaction(transaction);
  }


    public List<Transaction> getAllTransactionsForAccountByIban(String iban)
    {
        List<Transaction> returnTransactionList = new ArrayList<>();
        List<Transaction> transactionList = transactionRepo.findAll();

        for (Transaction t : transactionList)
        {
            if (t instanceof Deposit && ((Deposit) t).getAccountTo().equals(iban))
            {
                returnTransactionList.add(t);
            } else if (t instanceof RegularTransaction && ((RegularTransaction) t).getAccountTo().equals(iban))
            {
                returnTransactionList.add(t);
            } else if (t instanceof RegularTransaction && ((RegularTransaction) t).getAccountFrom().equals(iban))
            {
                returnTransactionList.add(t);
            } else if (t instanceof Withdrawal && ((Withdrawal) t).getAccountFrom().equals(iban))
            {
                returnTransactionList.add(t);
            }
        }
        return returnTransactionList;
    }

  public Deposit depositMoney(Deposit deposit) throws Exception {
    return performDeposit(deposit);
  }

  public Withdrawal withdrawMoney(Withdrawal withdrawal, User user) throws Exception {

    Account account = accountService.getAccountByIban(withdrawal.getAccountFrom());
    User accountOwner = userService.getUserByIban(account);

    if(user.getRole().equals(Role.ROLE_EMPLOYEE) || accountOwner.getId() == user.getId() ){
      return performWithdrawal(withdrawal);
    }
    else{
      throw new IllegalArgumentException("user is not authorized to do this");
    }
  }

  private Deposit performDeposit(Deposit deposit) throws Exception {
    // Assuming valid user
    // Assuming validation done in account service
    // Assuming deposit is a valid deposit
    accountService.addBalance(deposit.getAccountTo(), deposit.getAmount());
    return transactionRepo.save(deposit);
  }

  private Withdrawal performWithdrawal(Withdrawal withdrawal) throws Exception {
    // Assuming valid user
    // Assuming validation done in account
    // Assuming withdrawal is a valid withdrawal
    accountService.subtractBalance(withdrawal.getAccountFrom(), withdrawal.getAmount());
    return transactionRepo.save(withdrawal);
  }

  private RegularTransaction performRegularTransaction(RegularTransaction transaction) throws Exception {
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
      RegularTransaction transaction, boolean deductedFrom, boolean addedTo) throws Exception {
    if (deductedFrom) {
      accountService.addBalance(transaction.getAccountFrom(), transaction.getAmount());
    }
    if (addedTo) {
      accountService.subtractBalance(transaction.getAccountTo(), transaction.getAmount());
    }
  }

  private boolean isValidTransactionDate(Transaction transaction) {
    if (transaction.getTransactionDate() == null ||LocalDateTime.now().minusMinutes(3).isAfter(transaction.getTransactionDate())) {
      // Transaction is too old
      log.info("Transaction is too old");
      throw new IllegalStateException(
              String.format("Date %s is too old to be valid", transaction.getTransactionDate()));
    }
    return true;
  }
}
