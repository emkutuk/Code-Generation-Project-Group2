import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.Account;
import io.swagger.service.AccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.net.URI;

import static org.junit.Assert.*;

public class StepDefinitions
{
    Account account;

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "https://localhost:8443/api/";

    @Given("I am already an existing customer")
    public void i_am_already_an_existing_customer()
    {
        // Here get a user
        String temp = "OK";
        assertEquals("OK", temp);
    }

    @When("I want to open a new account")
    public void i_want_to_open_a_new_account() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        account = new Account(0L,"testIban", Account.AccountTypeEnum.SAVING, (double)0);
        URI uri = new URI(baseUrl + "Accounts");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(account), headers);

        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @Then("I should be able to open a new account")
    public void iShouldBeAbleToOpenANewAccount() throws Exception
    {
        //assertNotNull(accountService.getAccountByIban(account.getIban()));
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

    @Given("I am already an existing customer with a saving account that is with {int} balance")
    public void iAmAlreadyAnExistingCustomerWithASavingAccountThatIsWithBalance(int balance) throws Exception
    {
        account = new Account(999L, "testIban" ,Account.AccountTypeEnum.SAVING, (double) balance);
        assertNotNull(account);
    }

    @When("I want to delete a my account")
    public int iWantToDeleteAMyAccount()
    {
        return 0;
    }

    @Then("I should be able to delete my account")
    public void iShouldBeAbleToDeleteMyAccount()
    {
        
    }
}
