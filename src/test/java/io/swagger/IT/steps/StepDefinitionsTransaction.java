package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.*;
import io.swagger.security.JwtTokenProvider;
import io.swagger.service.UserService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class StepDefinitionsTransaction
{

    private String token;

    private RestTemplate template;
    private HttpHeaders headers;
    private HttpEntity<String> entity;
    private ResponseEntity<String> responseEntity;
    private ObjectMapper mapper;
    private String loginUrl = "http://localhost:8080/api/Login";
    private String transactionsUrl = "http://localhost:8080/api/Transactions";
    private UUID customerId;

    @Before
    public void setup() throws URISyntaxException, JsonProcessingException
    {
        template = new RestTemplate();
        headers = new HttpHeaders();
        mapper = new ObjectMapper();

        customerId = new UUID(new BigInteger("3fa85f6457174562b3fc2c963f66afa7".substring(0, 16), 16).longValue(), new BigInteger("3fa85f6457174562b3fc2c963f66afa7".substring(16), 16).longValue());
    }


    public void Login(String email, String password) throws Exception
    {
        //Login user
        ObjectMapper mapper = new ObjectMapper();
        LoginDto loginDto = new LoginDto(email, password);

        URI userUri = new URI(loginUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(loginDto), headers);

        responseEntity = template.postForEntity(userUri, entity, String.class);
        token = responseEntity.getBody();
    }

    @Given("I am A Customer")
    public void iAmACustomer() throws Exception
    {
        Login("testCucumber2", "testCucumber2");
    }


    @When("I want to return all my transactions with offset {string} and max {string}")
    public void iWantToReturnAllMyTransactionsWithOffsetAndMax(String offset, String max) throws URISyntaxException
    {
        String parameters = "?max="+max+"&offset="+offset+"0";
        URI uri = new URI(transactionsUrl + parameters);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        entity = new HttpEntity<>(headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @Then("Return HTTPStatus {int}")
    public void returnHTTPStatus(int statusCode)
    {
        Assert.assertEquals(statusCode, responseEntity.getStatusCodeValue());
    }


    @When("I want to transfer {string} Euro from my one account that has iban {string} to my another account that has iban {string}")
    public void iWantToTransferEuroFromMyOneAccountThatHasIbanToMyAnotherAccountThatHasIban(String amount, String accountFromIban, String accountToIban) throws URISyntaxException, JsonProcessingException, ParseException
    {
        URI uri = new URI(transactionsUrl);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        //String accountTo, String accountFrom, double amount, UUID performedBy)
        RegularTransaction transaction = new RegularTransaction(accountToIban, accountFromIban, Double.parseDouble(amount), customerId);
        /*DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date transactionDate = myFormat.parse(transaction.getTransactionDate().toString());
        transaction.setTransactionDate(transactionDate);
        System.out.println(transactionDate);*/

        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(transaction), headers);

        responseEntity = template.postForEntity(uri, entity, String.class);
    }
}
