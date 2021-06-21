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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {

  @InjectMocks private TransactionService transactionService;
  @Mock private UserService userService;
  @Mock private AccountService accountService;
  @Mock private TransactionRepo transactionRepo;

  private List<Transaction> expectedTransactionList;

  // Test Users and Accounts
  private Account accountToCurrent, accountToSavings, accountFromCurrent, accountFromSavings;
  private User customerTo, customerFrom, employee;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    // Create test accounts
    accountToCurrent = new Account("accountToCurrent", AccountType.CURRENT, 100d);
    accountToSavings = new Account("accountToSavings", AccountType.SAVING, 100d);
    accountFromCurrent = new Account("accountFromCurrent", AccountType.CURRENT, 100d);
    accountFromSavings = new Account("accountFromSavings", AccountType.SAVING, 100d);

    // create fake users
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
            "customerTo",
            "customerTo",
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

  @AfterEach
  void tearDown() {}

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
  public void
      createTransactionShouldNotAllowDatesOlderThan5MinutesPast_ThrowsIllegalStateException() {
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

  @ParameterizedTest
  @DisplayName("createTransaction should not allow accountTo that does not exist")
  @ValueSource(strings = {"", "fakeAccount", "123"})
  public void createTransactionShouldNotAllowAccountToThatDoNotExist_ThrowsIllegalArgumentException(
      String account) {

    // Service Mocking
    when(accountService.getAccountByIban(accountToCurrent.getIban())).thenReturn(accountToCurrent);
    when(accountService.getAccountByIban(accountFromCurrent.getIban())).thenReturn(accountToCurrent);
    when(userService.getUserByIban(accountFromCurrent)).thenReturn(customerFrom);

    RegularTransaction expectedTransaction =
        new RegularTransaction(
            account, accountFromCurrent.getIban(), 400d, employee.getId(), LocalDateTime.now());
    assertThrows(
        IllegalArgumentException.class,
        () -> transactionService.createTransaction(expectedTransaction, employee));
  }

  @ParameterizedTest
  @DisplayName("createTransaction should not allow accountFrom that does not exist")
  @ValueSource(strings = {"", "fakeAccount", "123"})
  public void
      createTransactionShouldNotAllowAccountFromThatDoNotExist_ThrowsIllegalArgumentException(
          String account) {
    RegularTransaction expectedTransaction =
        new RegularTransaction(account, "", 400d, UUID.randomUUID(), LocalDateTime.now());
    User fakeUser = new User();
    assertThrows(
        IllegalArgumentException.class,
        () -> transactionService.createTransaction(expectedTransaction, fakeUser));
  }

  @Test
  @DisplayName("Valid transaction between two current accounts performed by employee")
  public void createTransactionTest() throws Exception {
    RegularTransaction expectedTransaction =
        new RegularTransaction(
            accountToCurrent.getIban(),
            accountFromCurrent.getIban(),
            400d,
            employee.getId(),
            LocalDateTime.now());

    // Service Mocking
    when(accountService.getAccountByIban(accountToCurrent.getIban())).thenReturn(accountToCurrent);
    when(accountService.getAccountByIban(accountFromCurrent.getIban())).thenReturn(accountFromCurrent);
    when(userService.getUserByIban(accountFromCurrent)).thenReturn(customerFrom);
    when(transactionRepo.save(expectedTransaction)).thenReturn(expectedTransaction);

    Transaction returnedTransaction = transactionService.createTransaction(expectedTransaction, employee);

    assertNotNull(returnedTransaction);
    assertEquals(expectedTransaction, returnedTransaction);
  }

  @Test
  void createTransaction() {
    // account not exist bad
    // user not exist bad
    // user not own account bad
    // savings to savings bad

    // own accounts good
    // employee
  }

  @Test
  void getAllTransactionsForAccountByIban() {}

  @Test
  void depositMoney() {}

  @Test
  void withdrawMoney() {}

  @Test
  void addTransactions() {}
}
