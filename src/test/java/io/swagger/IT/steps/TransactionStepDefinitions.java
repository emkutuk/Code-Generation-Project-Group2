package io.swagger.IT.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

public class TransactionStepDefinitions {

  @Autowired private MockMvc mvc;
  private RequestBuilder request;
  private MvcResult result;
  private HttpHeaders httpHeaders;
  private ObjectMapper objectMapper;

  private Account accountToCurrent, accountToSavings, accountFromCurrent, accountFromSavings;
  private User customerTo, customerFrom, employee;

  @Given("I am already an existing customer")
  public void iAmAlreadyAnExistingCustomer() {

  }

  @And("I have a current account")
  public void iHaveACurrentAccount() {

    accountFromCurrent = new Account("NL99INHO9999999999", AccountType.CURRENT, 500d);
    // Add Account
    // Check added
  }

  @And("I have a savings account")
  public void iHaveASavingsAccount() {

    accountFromSavings = new Account("NL99INHO9999999998", AccountType.CURRENT, 500d);
    // Add Account
    // Check Added
  }

  @And("I am authorized")
  public void iAmAuthorized() {
    // Login Customer
    // Check login success
    // Add header to Request
    // Check header
  }

  @When("I create a transaction")
  public void iCreateATransaction() {
    //transaction = new RegularTransaction();
  }

  @Then("The money is transferred between my accounts")
  public void theMoneyIsTransferredBetweenMyAccounts() {
    // Perform transaction
  }

  @And("The transaction is stored in the database")
  public void theTransactionIsStoredInTheDatabase() {
    // Get Transaction and compare to original
  }

  @And("I can see the transaction")
  public void iCanSeeTheTransaction() {
    // check transaction is returned in the response
  }

  @When("I deposit money")
  public void iDepositMoney() {
    // Create Deposit
  }

  @Then("It is stored in my account")
  public void itIsStoredInMyAccount() {
    // Perform Deposit
  }

  @When("I withdraw money")
  public void iWithdrawMoney() {
    // Create Withdrawal
  }

  @Then("The money is remove from my account")
  public void theMoneyIsRemoveFromMyAccount() {
    // Perform Withdrawal
  }

  @And("The other account exists")
  public void theOtherAccountExists() {
    // Create other user and Account
  }

  @Then("The money is removed my account")
  public void theMoneyIsRemovedMyAccount() {
    // Check Money is removed
  }

  @And("The money is added to the other account")
  public void theMoneyIsAddedToTheOtherAccount() {
    // Check Money is added
  }
}
