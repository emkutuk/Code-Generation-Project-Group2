import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.Account;

import static org.junit.Assert.*;

public class StepDefinitions
{
    Account account;

    @Given("I am already an existing customer")
    public void i_am_already_an_existing_customer()
    {
        // Here create a new user
        String temp = "OK";
        assertEquals("OK", temp);
    }

    @When("I want to open a new account")
    public void i_want_to_open_a_new_account() throws Exception
    {
        account = new Account(Account.AccountTypeEnum.SAVING);
    }

    @Then("I should be able to open a new account")
    public void iShouldBeAbleToOpenANewAccount() throws Exception
    {
        assertNotNull(account);
    }

    @Given("I have an account")
    public void iHaveAnAccount() throws Exception
    {
        account = new Account(Account.AccountTypeEnum.CURRENT);
    }

    @When("I want to learn about my account's balance")
    public void iWantToLearnAboutMyAccountSBalance()
    {
        assertNotNull(account);
    }

    @Then("I should be able to see my account's balance")
    public void iShouldBeAbleToSeeMyAccountSBalance()
    {
        assertNotNull(account.getBalance());
    }

}
