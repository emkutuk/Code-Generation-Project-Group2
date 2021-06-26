package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.*;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class StepDefinitionsAccount
{
    private String token;
    private String actualBalance;

    private String actualResult;

    private RestTemplate template = new RestTemplate();
    private String loginUrl = "http://localhost:8080/api/Login";
    private String userUrl = "http://localhost:8080/api/Users";
    private String accountUrl = "http://localhost:8080/api/Accounts";
    private HttpHeaders headers = new HttpHeaders();

    private ResponseEntity<String> responseEntity;

    //Scenario: As a customer I would like to create a saving account
    @Given("I have an account")
    public void iHaveAnAccount() throws Exception
    {
        //Login user
        ObjectMapper mapper = new ObjectMapper();
        LoginDto loginDto = new LoginDto("customer", "customer");

        URI userUri = new URI(loginUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(loginDto), headers);

        responseEntity = template.postForEntity(userUri, entity, String.class);
        token = responseEntity.getBody();
    }

    @When("I want to learn about my account's balance")
    public void iWantToLearnAboutMyAccountSBalance() throws IOException, URISyntaxException
    {
        URI uri = new URI(accountUrl + "/NL01INHO0000000053/Balance");

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
        actualBalance = responseEntity.getBody();
    }

    @Then("I should be able to see my account's balance")
    public void iShouldBeAbleToSeeMyAccountSBalance()
    {
        Assert.assertEquals(actualBalance, "500.0");
    }

    //Scenario: As a customer I would like to close my saving account
    @Given("I am already an existing customer with a saving account that is with zero balance")
    public void iAmAlreadyAnExistingCustomerWithASavingAccountThatIsWithZeroBalance() throws JsonProcessingException, URISyntaxException
    {
        //Login user
        ObjectMapper mapper = new ObjectMapper();
        LoginDto loginDto = new LoginDto("customer", "customer");

        URI userUri = new URI(loginUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(loginDto), headers);

        responseEntity = template.postForEntity(userUri, entity, String.class);
        token = responseEntity.getBody();
    }

    @When("I want to close my account")
    public void iWantToCloseMyAccount() throws URISyntaxException
    {
        URI uri = new URI(accountUrl + "/NL01INHO0000000053/Status/disabled");

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
        actualResult = responseEntity.getStatusCode().toString();
    }

    @Then("I should be able to delete my account")
    public void iShouldBeAbleToDeleteMyAccount()
    {
        Assert.assertEquals("200 OK", actualResult);
    }

    @Given("I am a customer")
    public void iAmACustomer()
    {
    }

    @When("I want to open a new account")
    public void iWantToOpenANewAccount()
    {
        //Create an account, add it to the customer
    }

    @Then("I should be able to open a new account")
    public void iShouldBeAbleToOpenANewAccount()
    {
        //Check if testIban belongs to that customer
    }


}
