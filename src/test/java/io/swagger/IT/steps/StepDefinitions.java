package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class StepDefinitions
{
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "https://localhost:443/api";


    @Given("I am already an existing customer")
    public void i_am_already_an_existing_customer() throws Exception
    {
        URI uri = new URI(baseUrl + "/Accounts");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @When("I want to open a new account")
    public void i_want_to_open_a_new_account() throws Exception
    {

    }

    @Then("I should be able to open a new account")
    public void iShouldBeAbleToOpenANewAccount() throws Exception
    {

    }
}
