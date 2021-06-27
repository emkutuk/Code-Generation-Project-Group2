package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repo.TransactionRepo;
import io.swagger.security.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {

  @InjectMocks private TransactionService transactionService;
  @Mock private UserService userService;
  @Mock private AccountService accountService;
  @Mock private TransactionRepo transactionRepo;

  private List<Transaction> expectedTransactionList;

  // Test Users, Accounts & Transactions
  private Account accountToCurrent, accountToSavings, accountFromCurrent, accountFromSavings;
  private User customerTo, customerFrom, employee;

  // Performed by employee by default
  private RegularTransaction transactionCurrentToCurrent,
      transactionCurrentToSavings,
      transactionSavingsToCurrent,
      transactionSavingsToSavings;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    // Create test accounts
    accountSetup();
    // create fake users
    userSetup();
    // Transaction Templates
    transactionSetup();
    // Service Mocking
    serviceSetup();
  }

  private void serviceSetup() throws Exception {

    // Account Service
    when(accountService.getAccountByIban(accountToCurrent.getIban())).thenReturn(accountToCurrent);
    when(accountService.getAccountByIban(accountFromCurrent.getIban()))
        .thenReturn(accountFromCurrent);
    when(accountService.getAccountByIban(accountToSavings.getIban())).thenReturn(accountToSavings);
    when(accountService.getAccountByIban(accountFromSavings.getIban()))
        .thenReturn(accountFromSavings);

    // User service
    when(userService.getUserByIban(accountToCurrent)).thenReturn(customerTo);
    when(userService.getUserByIban(accountToSavings)).thenReturn(customerTo);
    when(userService.getUserByIban(accountFromCurrent)).thenReturn(customerFrom);
    when(userService.getUserByIban(accountFromSavings)).thenReturn(customerFrom);

    // Transaction Repo
    when(transactionRepo.save(transactionCurrentToCurrent)).thenReturn(transactionCurrentToCurrent);
    when(transactionRepo.save(transactionCurrentToSavings)).thenReturn(transactionCurrentToSavings);
    when(transactionRepo.save(transactionSavingsToCurrent)).thenReturn(transactionSavingsToCurrent);
    when(transactionRepo.save(transactionSavingsToSavings)).thenReturn(transactionSavingsToSavings);
    when(transactionRepo.findAll()).thenReturn(expectedTransactionList);
  }

  private void accountSetup() {
    accountToCurrent = new Account("accountToCurrent", AccountType.CURRENT, 1000d);
    accountToSavings = new Account("accountToSavings", AccountType.SAVING, 1000d);
    accountFromCurrent = new Account("accountFromCurrent", AccountType.CURRENT, 1000d);
    accountFromSavings = new Account("accountFromSavings", AccountType.SAVING, 1000d);
  }

  private void userSetup() {
    customerTo =
        new User(
            "Customer",
            "To",
            "12345",
            "customerTo",
            "customerTo",
            new ArrayList<Account>() {
              {
                add(accountToCurrent);
                add(accountToSavings);
              }
            },
            Role.ROLE_CUSTOMER,
            AccountStatus.ACTIVE);
    customerFrom =
        new User(
            "Customer",
            "From",
            "12345",
            "customerFrom",
            "customerFrom",
            new ArrayList<Account>() {
              {
                add(accountFromCurrent);
                add(accountFromSavings);
              }
            },
            Role.ROLE_CUSTOMER,
            AccountStatus.ACTIVE);
    employee =
        new User(
            "Employee",
            "Employee",
            "12345",
            "Employee",
            "Employee",
            null,
            Role.ROLE_EMPLOYEE,
            AccountStatus.ACTIVE);
  }

  private void transactionSetup() {
    transactionCurrentToCurrent =
        new RegularTransaction(
            accountToCurrent.getIban(),
            accountFromCurrent.getIban(),
            400d,
            employee.getId(),
            LocalDateTime.now());

    transactionCurrentToSavings =
        new RegularTransaction(
            accountToSavings.getIban(),
            accountFromCurrent.getIban(),
            400d,
            employee.getId(),
            LocalDateTime.now());

    transactionSavingsToCurrent =
        new RegularTransaction(
            accountToCurrent.getIban(),
            accountFromSavings.getIban(),
            400d,
            employee.getId(),
            LocalDateTime.now());

    transactionSavingsToSavings =
        new RegularTransaction(
            accountToSavings.getIban(),
            accountFromSavings.getIban(),
            400d,
            employee.getId(),
            LocalDateTime.now());

    expectedTransactionList =
        new ArrayList<Transaction>() {
          {
            add(transactionCurrentToCurrent);
            add(transactionCurrentToSavings);
            add(transactionSavingsToCurrent);
            add(transactionSavingsToSavings);
          }
        };
  }

  @AfterEach
  void tearDown() {
    // Reset for each test
    accountToCurrent = accountToSavings = accountFromCurrent = accountFromSavings = null;
    customerTo = customerFrom = employee = null;
    transactionCurrentToCurrent =
        transactionCurrentToSavings =
            transactionSavingsToCurrent = transactionSavingsToSavings = null;
    expectedTransactionList = null;
  }

  /*  @Test
  void getTransactions()
  {

  }

  @Test
  void getTransactionById() {
  }

  @Test
  void getTransactionsByUserId() {
  }

  @Test
  void getTransactionsByIban() {
  }

  @Test
  void deleteTransactionById() {
  }

  @Test
  void getTransactionsPaginated() {
  }*/

  @Test
  @DisplayName("createTransaction throws Illegal state exception if date older than 5 minutes")
  public void
      createTransactionShouldNotAllowDatesOlderThan5MinutesPast_ThrowsIllegalStateException() {

    // Date can only be set in constructor
    RegularTransaction expectedTransaction =
        new RegularTransaction(
            accountToCurrent.getIban(),
            accountToCurrent.getIban(),
            400d,
            employee.getId(),
            LocalDateTime.now().minusMinutes(15));

    assertThrows(
        IllegalStateException.class,
        () -> transactionService.createTransaction(expectedTransaction, employee));
  }

  @Test
  @DisplayName("createTransaction should not allow accountTo that does not exist")
  public void
      createTransactionShouldNotAllowAccountToThatDoNotExist_ThrowsIllegalArgumentException() {

    transactionCurrentToCurrent.setAccountTo("ThisAccountDoesNotExist");

    assertThrows(
        IllegalArgumentException.class,
        () -> transactionService.createTransaction(transactionCurrentToCurrent, employee));
  }

  @ParameterizedTest
  @DisplayName("createTransaction should not allow accountFrom that does not exist")
  @ValueSource(strings = {"", "fakeAccount", "123"})
  public void
      createTransactionShouldNotAllowAccountFromThatDoNotExist_ThrowsIllegalArgumentException(
          String account) {
    RegularTransaction expectedTransaction =
        new RegularTransaction(account, "", 400d, UUID.randomUUID(), LocalDateTime.now());
    assertThrows(
        IllegalArgumentException.class,
        () -> transactionService.createTransaction(expectedTransaction, employee));
  }

  @Test
  @DisplayName("Valid transaction between two current accounts performed by employee")
  public void createTransactionTest() throws Exception {

    Transaction returnedTransaction =
        transactionService.createTransaction(transactionCurrentToCurrent, employee);

    assertNotNull(returnedTransaction);
    assertEquals(transactionCurrentToCurrent, returnedTransaction);
  }

  @Test
  @DisplayName(
      "createTransaction should not allow customer to transfer from account that is not theirs")
  public void
      createTransactionShouldNotAllowUserToTransferFromAccountThatIsNotTheirs_ThrowsIllegalArgumentException() {

    transactionCurrentToCurrent.setAccountFrom(accountFromCurrent.getIban());
    transactionCurrentToCurrent.setPerformedBy(customerTo.getId());
    assertThrows(
        IllegalArgumentException.class,
        () -> transactionService.createTransaction(transactionCurrentToCurrent, customerTo));
  }

  @Test
  @DisplayName(
      "createTransaction should now allow to transfer between savings account if not same user")
  public void
      createTransactionShouldNotAllowUserToTransferFromSavingsAccountToAnotherSavingsAccount_ThrowsIllegalArgumentException() {

    // Savings to another users savings
    assertThrows(
        IllegalArgumentException.class,
        () -> transactionService.createTransaction(transactionSavingsToSavings, employee));

    transactionSavingsToSavings.setAccountTo(accountFromSavings.getIban());

    // Savings to same user (same account)
    assertDoesNotThrow(
        () -> transactionService.createTransaction(transactionSavingsToSavings, employee));
  }

  @Test
  @DisplayName("")
  public void undoTransactionShouldBeCalledWhenErrorThrown() throws Exception {
    when(accountService.subtractBalance(
            transactionCurrentToCurrent.getAccountFrom(), transactionCurrentToCurrent.getAmount()))
        .thenThrow(new Exception());

    assertThrows(Exception.class, () -> transactionService.createTransaction(transactionCurrentToCurrent, employee));



    verify(accountService).subtractBalance(Mockito.anyString(), Mockito.anyDouble());
  }

  @Test
  @DisplayName("Withdrawmoney")
  public void withdrawMoney() throws Exception {
    Withdrawal withdrawal = new Withdrawal();
    transactionService.withdrawMoney(withdrawal, employee);
  }

  @Test
  void getAllTransactionsForAccountByIban() {}

  @Test
  @DisplayName("Deposit money")
  void depositMoney() throws Exception {
    Deposit deposit = new Deposit();
    transactionService.depositMoney(deposit);
  }

  @Test
  void addTransactions()
  {

  }
}
