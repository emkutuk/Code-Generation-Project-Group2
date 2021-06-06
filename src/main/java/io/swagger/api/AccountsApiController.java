package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.repo.AccountRepo;
import io.swagger.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi
{

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    AccountService accountService;


    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request)
    {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Account> addANewAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Account account) throws Exception
    {
        try
        {
            accountService.addANewAccount(account);
            return new ResponseEntity<Account>(HttpStatus.CREATED).status(201).body(account);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> changeAccountType(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.PATH, description = "The new type for the account to be changed into.", required = true, schema = @Schema(allowableValues = {"saving", "current"})) @PathVariable("type") String type)
    {
        try
        {
            accountService.changeAccountType(iban, type);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //0 = Success
    //1 = Balance is not 0
    //2 = NotFound
    public ResponseEntity<Void> deleteAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required = true, schema = @Schema()) @PathVariable("iban") String iban)
    {
        // 99 is random here
        int result = 99;
        try
        {
            result = accountService.deleteAccountByIban(iban);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (result == 0) return new ResponseEntity<Void>(HttpStatus.OK);
        else if (result == 1) return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        else if (result == 2) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Double> getAccountBalanceByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required = true, schema = @Schema()) @PathVariable("iban") String iban)
    {
        try
        {
            //Method returns null from service when there is no account with that iban
            Double balance = accountService.getAccountBalanceByIban(iban);
            if (balance.equals(null))
            {
                return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
            } else return new ResponseEntity<Double>(HttpStatus.OK).status(200).body(balance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Account> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required = true, schema = @Schema()) @PathVariable("iban") String iban) throws Exception
    {
        try
        {
            Account account = accountService.getAccountByIban(iban);
            return new ResponseEntity<Account>(HttpStatus.CREATED).status(200).body(account);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Account>> getAllAccounts(@Min(0) @Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set.", schema = @Schema(allowableValues = {})) @Valid @RequestParam(value = "offset", required = false) Integer offset, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return.", schema = @Schema(allowableValues = {}, minimum = "10", maximum = "50", defaultValue = "10")) @Valid @RequestParam(value = "max", required = false, defaultValue = "10") Integer max)
    {
        List<Account> accountsList = new ArrayList<Account>();
        List<Account> allAccounts = new ArrayList<Account>();
        try
        {
            allAccounts = accountService.getAllAccounts();

            long maxValue = max + offset;

            if (maxValue > allAccounts.stream().count()) maxValue = allAccounts.stream().count();

            for (int i = offset; i < maxValue; i++)
                accountsList.add(allAccounts.get(i));

            return new ResponseEntity<List<Account>>(HttpStatus.OK).status(200).body(accountsList);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Account account)
    {
        try
        {
            accountService.updateAccountByIban(iban, account);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
