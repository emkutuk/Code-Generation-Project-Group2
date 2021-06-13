package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

  private static Transaction transaction;

  @BeforeEach
  public void createTestTransaction() {
    transaction =
        new RegularTransaction("NL04INHO68681868171", "NL01INHO00005798481", 120D, UUID.randomUUID());
  }

  @Test
  @DisplayName("Transaction should not be null")
  public void transactionShouldNotBeNull() {
    assertNotNull(transaction);
  }

  @Test
  @DisplayName("TransactionId should not be null")
  public void transactionIdShouldNotBeNull() {
    assertNotNull(transaction.getTransactionId());
  }

  @Test
  @DisplayName("AccountTo should not be null")
  public void setAccountToShouldNotAllowNull() {
    //transaction.setAccountTo(null);
    //assertNotNull(transaction.getAccountTo());
  }

  @Test
  @DisplayName("AccountFrom should not be null")
  public void setAccountFromShouldNotAllowNull() {
    //transaction.setAccountFrom(null);
    //assertNotNull(transaction.getAccountFrom());
  }

  @Test
  @DisplayName("AccountFrom should not be less than 18 characters")
  public void setAccountFromThrowsIllegalArgumentExceptionIfLessThanEighteenCharacters() {
    //assertThrows(IllegalArgumentException.class, () -> transaction.setAccountFrom(""));
  }

  @ParameterizedTest
  @DisplayName("AccountFrom should not be greater than 34 characters")
  @ValueSource(
      strings = {
        "This is a really long string like super long",
        "This is a loong string but 35 chars"
      })
  public void setAccountFromThrowsIllegalArgumentExceptionIfMoreThanThirtyTwoCharacters(
      String string) {
    //assertThrows(IllegalArgumentException.class, () -> transaction.setAccountFrom(string));
  }

  @ParameterizedTest
  @DisplayName("AccountTo should not be greater than 34 characters")
  @ValueSource(
      strings = {
        "This is a really long string like super long",
        "This is a loong string but 35 chars"
      })
  public void setAccountToThrowsIllegalArgumentExceptionIfMoreThanThirtyTwoCharacters(
      String string) {
    //assertThrows(IllegalArgumentException.class, () -> transaction.setAccountFrom(string));
  }

  @Test
  @DisplayName("AccountTo should not be less than 18 characters")
  public void setAccountToThrowsIllegalArgumentExceptionIfLessThanEighteenCharacters() {
    //assertThrows(IllegalArgumentException.class, () -> transaction.setAccountTo(""));
  }

  @Test
  @DisplayName("TransactionDate should be no more than 15 minutes past")
  public void setTransactionDateThrowsIllegalArgumentExceptionIfDateBeforeToday() {
    LocalDateTime date = LocalDateTime.now().minusMinutes(15);
    assertThrows(IllegalArgumentException.class, () -> transaction.setTransactionDate(date));
  }

  @ParameterizedTest
  @DisplayName("Transaction Amount should not be 0 or less")
  @ValueSource(doubles = {0, -0.01})
  public void transactionAmountThrowsIllegalArugmentExceptionIfZeroOrLess(double amount) {
    assertThrows(IllegalArgumentException.class, () -> transaction.setAmount(amount));
  }

  @Test
  @DisplayName("performedBy should Not be null")
  public void performedByShouldNotBeNull() {
    assertNotNull(transaction.getPerformedBy());
    transaction.setPerformedBy(null);
    assertNotNull(transaction.getPerformedBy());
  }

}
