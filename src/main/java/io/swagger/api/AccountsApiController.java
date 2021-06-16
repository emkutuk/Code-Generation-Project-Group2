package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-10T11:32:06.118Z[GMT]")
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


    public ResponseEntity<Account> addANewAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Account account)
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

    public ResponseEntity<Void> changeAccountType(@Size(max = 34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.PATH, description = "The new type for the account to be changed into.", required = true, schema = @Schema(allowableValues = {"saving", "current"})) @PathVariable("type") String type)
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

    public ResponseEntity<Void> changeAccountStatus(@Size(max = 34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.PATH, description = "Account status to be changed to.", required = true, schema = @Schema(allowableValues = {"active", "closed"})) @PathVariable("status") String status)
    {
        try
        {
            accountService.changeAccountStatus(iban, status);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Double> getAccountBalanceByIban(@Size(max = 34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required = true, schema = @Schema()) @PathVariable("iban") String iban)
    {
        try
        {
            //Method returns null from service when there is no account with that iban
            Double balance = accountService.getAccountBalanceByIban(iban);
            if (balance.equals(null)) return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<Double>(HttpStatus.OK).status(200).body(balance);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Account> getAccountByIban(@Size(max = 34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required = true, schema = @Schema()) @PathVariable("iban") String iban)
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

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
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

    public ResponseEntity<Void> updateAccountByIban(@Size(max = 34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Account account)
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
