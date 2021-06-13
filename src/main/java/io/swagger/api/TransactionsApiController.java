package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import io.swagger.service.AccountService;
import io.swagger.service.TransactionService;
import io.swagger.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-06T11:20:30.422Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi
{

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request)
    {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RegularTransaction> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody RegularTransaction transaction)
    {

        String accept = request.getHeader("Accept");
        String tokenHeader = request.getHeader("bearerToken");

        tokenHeader = "testToken";

        if (tokenHeader == null || tokenHeader.isEmpty())
        {
            log.info("Unauthorised");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else
        {

            log.info("Would be checking for if token is valid");
            // If not valid token
            // return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = new User(); // Get User from token
        // null check user

        if (accept != null && accept.contains("application/json"))
        {
            try
            {

                log.info("should be creating transaction");
                return new ResponseEntity<>(transactionService.createTransaction(transaction, user), HttpStatus.CREATED);
            } catch (Exception e)
            {
                e.printStackTrace();
                // Handle exceptions
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<RegularTransaction>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> deleteTransactionById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id)
    {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Deposit> depositMoney(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Deposit deposit)
    {

        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                log.info("Trying to save deposit");
                return new ResponseEntity<Deposit>(transactionService.depositMoney(deposit), HttpStatus.CREATED);
            } catch (Exception e)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<Deposit>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Transaction>> getTransactionByIBAN(@Size(max = 34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return.", schema = @Schema(allowableValues = {}, minimum = "10", maximum = "50", defaultValue = "10")) @Valid @RequestParam(value = "max", required = false, defaultValue = "10") Integer max, @Min(0) @Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set.", schema = @Schema(allowableValues = {})) @Valid @RequestParam(value = "offset", required = false) Integer offset)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                return new ResponseEntity<List<Transaction>>(new ArrayList<Transaction>(), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e)
            {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> getTransactionById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                return new ResponseEntity<Transaction>(new RegularTransaction(), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e)
            {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransactionsByUser(@Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return.", schema = @Schema(allowableValues = {}, minimum = "10", maximum = "50", defaultValue = "10")) @Valid @RequestParam(value = "max", required = false, defaultValue = "10") Integer max, @Min(0) @Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set.", schema = @Schema(allowableValues = {})) @Valid @RequestParam(value = "offset", required = false) Integer offset)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                return new ResponseEntity<List<Transaction>>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e)
            {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Withdrawal> withdrawMoney(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Withdrawal withdrawal)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                log.info("Trying to save withdrawal");
                return new ResponseEntity<Withdrawal>(transactionService.withdrawMoney(withdrawal), HttpStatus.OK);
            } catch (Exception e)
            {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Withdrawal>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<Withdrawal>(HttpStatus.BAD_REQUEST);
    }
}
