package io.swagger.api;

import io.swagger.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionsApiControllerTest
{

  @Autowired
  TransactionService transactionService;

  @BeforeEach
  public void init(){
    //transactionService = new TransactionService();
  }

  @Test
  void createTransaction() {
  }

  @Test
  void getTransactionsByUser() {
  }

  @Test
  void deleteTransactionById() {
  }

  @Test
  void depositMoney() {
  }

  @Test
  void editTransactionById() {
  }

  @Test
  void getTransactionByIBAN() {
  }

  @Test
  void getTransactionById() {
  }

  @Test
  void withdrawMoney() {
  }
}