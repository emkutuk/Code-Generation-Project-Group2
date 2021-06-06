package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Deposit;
import io.swagger.model.Transaction;
import io.swagger.model.Withdrawal;
import io.swagger.service.TransactionService;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi {

  private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private TransactionService transactionService;

  @org.springframework.beans.factory.annotation.Autowired
  public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }


  public ResponseEntity<Transaction> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Transaction transaction) {

    // Header location called Accept should contain application/json as value
    String accept = request.getHeader("Accept");
    // Should go into this
    if (accept != null && accept.contains("application/json")) {
      try {
        log.info("Creating transaction id" + transaction.getTransactionId());
        return new ResponseEntity<Transaction>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
      } catch (Exception e) {
        // Throw different exceptions for Error codes
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST); // Request did not contain valid JSON
  }

  public ResponseEntity<List<Transaction>> getTransactionsByUser(@Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return.", schema = @Schema(allowableValues = {}, minimum = "10", maximum = "50"
          , defaultValue = "10")) @Valid @RequestParam(value = "max", required = false, defaultValue = "10") Integer max, @Min(0) @Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set.", schema = @Schema(allowableValues = {}
  )) @Valid @RequestParam(value = "offset", required = false) Integer offset) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        // needs to be by user
        return new ResponseEntity<List<Transaction>>(transactionService.getTransactions(), HttpStatus.OK);

      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    log.info("Trying to get Transactions");
    transactionService.getTransactions().forEach(System.out::println);
    return new ResponseEntity<List<Transaction>>(transactionService.getTransactions(), HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Void> deleteTransactionById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    //Converting string to UUID because inbuilt UUID function returns a 'non valid UUID'
    String UUID = id.toString();
    String UUID2 = UUID.replace("-", "");
    UUID uuid = new UUID(
            new BigInteger(UUID2.substring(0, 16), 16).longValue(),
            new BigInteger(UUID2.substring(16), 16).longValue());
    try{
      transactionService.deleteTransactionById(uuid);
      return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
    catch (Exception e) {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<Transaction> depositMoney(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Transaction body) throws Exception {
    String accept = request.getHeader("Accept");
    try{
      Transaction transaction = transactionService.depositMoney(body.getAccountTo(),body.getAccountFrom(),body.getAmount(), body.getPerformedBy());
      if (transaction == null){
        return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<Transaction>(HttpStatus.OK);
    }
    catch (Exception e){
      log.error(e.toString());
    }
    return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<Transaction> editTransactionById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Transaction transaction) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Transaction>(transactionService.editTransactionById(UUID.fromString(id),transaction), HttpStatus.OK);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Transaction>> getTransactionByIBAN(@NotNull @Size(max = 34) @Parameter(in = ParameterIn.QUERY, description = "The account to perform the action on.", required = true, schema = @Schema()) @Valid @RequestParam(value = "IBAN", required = true) String IBAN, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return.", schema = @Schema(allowableValues = {}, minimum = "10", maximum = "50"
          , defaultValue = "10")) @Valid @RequestParam(value = "max", required = false, defaultValue = "10") Integer max, @Min(0) @Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set.", schema = @Schema(allowableValues = {}
  )) @Valid @RequestParam(value = "offset", required = false) Integer offset) {
    String accept = request.getHeader("Accept");
    List<Transaction> transactions = new ArrayList<Transaction>();
    try{
      transactions = transactionService.getTransactionsByIban(IBAN);
    }
    catch (Exception e1){
      log.error("Unable to call service", e1);
    }
      if (transactions.isEmpty()){
        return new ResponseEntity<List<Transaction>>(HttpStatus.BAD_REQUEST);
      }
      else {
        try {
          return new ResponseEntity<List<Transaction>>(HttpStatus.OK).status(200).body(transactions);
        } catch (Exception e) {
          log.error("Couldn't serialize response for content type application/json", e);
          return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
  }

  public ResponseEntity<Transaction> getTransactionById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Transaction>(transactionService.getTransactionById(UUID.fromString(id)), HttpStatus.OK);
      } catch (Exception e) {

        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Transaction> withdrawMoney(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Transaction body) throws Exception {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        Transaction transaction = transactionService.withdrawMoney(body.getAccountFrom(),body.getAmount(), body.getPerformedBy());
        if (transaction == null){
          return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Transaction>(HttpStatus.OK);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
  }

}