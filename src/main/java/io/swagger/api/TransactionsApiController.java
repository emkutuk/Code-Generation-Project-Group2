package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Deposit;
import io.swagger.model.RegularTransaction;
import io.swagger.model.Transaction;
import io.swagger.model.Withdrawal;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-06-06T11:20:30.422Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi {

  private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<RegularTransaction> createTransaction(
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema())
          @Valid
          @RequestBody
          RegularTransaction body) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<RegularTransaction>(
            objectMapper.readValue(
                "{\n  \"accountTo\" : \"NL04INHO6818968668\",\n  \"accountFrom\" : \"NL01INHO0000579848\"\n}",
                RegularTransaction.class),
            HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<RegularTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<RegularTransaction>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Void> deleteTransactionById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id) {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Deposit> depositMoney(
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema())
          @Valid
          @RequestBody
          Deposit body) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Deposit>(
            objectMapper.readValue("{\n  \"accountTo\" : \"NL04INHO6818968668\"\n}", Deposit.class),
            HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Deposit>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Deposit>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<RegularTransaction> editTransactionById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id,
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema())
          @Valid
          @RequestBody
          RegularTransaction body) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<RegularTransaction>(HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<RegularTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<RegularTransaction>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Transaction>> getTransactionByIBAN(
      @Size(max = 34)
          @Parameter(
              in = ParameterIn.PATH,
              description = "The account to perform the action on.",
              required = true,
              schema = @Schema())
          @PathVariable("iban")
          String iban,
      @Min(10)
          @Max(50)
          @Parameter(
              in = ParameterIn.QUERY,
              description = "The maximum number of items to return.",
              schema =
                  @Schema(
                      allowableValues = {},
                      minimum = "10",
                      maximum = "50",
                      defaultValue = "10"))
          @Valid
          @RequestParam(value = "max", required = false, defaultValue = "10")
          Integer max,
      @Min(0)
          @Parameter(
              in = ParameterIn.QUERY,
              description =
                  "The number of items to skip before starting to collect the result set.",
              schema = @Schema(allowableValues = {}))
          @Valid
          @RequestParam(value = "offset", required = false)
          Integer offset) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Transaction>>(
            new ArrayList<Transaction>(), HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Transaction> getTransactionById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Transaction>(
            new RegularTransaction(), HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Transaction>> getTransactionsByUser(
      @Min(10)
          @Max(50)
          @Parameter(
              in = ParameterIn.QUERY,
              description = "The maximum number of items to return.",
              schema =
                  @Schema(
                      allowableValues = {},
                      minimum = "10",
                      maximum = "50",
                      defaultValue = "10"))
          @Valid
          @RequestParam(value = "max", required = false, defaultValue = "10")
          Integer max,
      @Min(0)
          @Parameter(
              in = ParameterIn.QUERY,
              description =
                  "The number of items to skip before starting to collect the result set.",
              schema = @Schema(allowableValues = {}))
          @Valid
          @RequestParam(value = "offset", required = false)
          Integer offset) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Transaction>>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Withdrawal> withdrawMoney(
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema())
          @Valid
          @RequestBody
          Withdrawal body) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Withdrawal>(HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Withdrawal>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Withdrawal>(HttpStatus.NOT_IMPLEMENTED);
  }
}
