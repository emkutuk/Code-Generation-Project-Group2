package io.swagger.service;

import io.swagger.repo.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class TransactionServiceTest {
  @MockBean AccountRepo accountRepo;

  @BeforeEach
  public void init() {}

  @Test
  void getTransactions() {}

  @Test
  void getTransactionsPaginated() {}

  @Test
  void getTransactionById() {}

  @Test
  void getTransactionsByUserId() {}

  @Test
  void createTransaction() {}

  @Test
  void getTransactionsByIban() {}

  @Test
  void depositMoney() {}

  @Test
  void withdrawMoney() {}

  @Test
  void deleteTransactionById() {}

  @Test
  void editTransactionById() {}

  @Test
  void addTransactions() {}

  @Test
  void undoTransaction() {}
}
