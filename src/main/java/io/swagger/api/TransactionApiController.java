package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Transaction;
import io.swagger.model.TransactionList;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")
@RestController
public class TransactionApiController implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Transaction> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Transaction body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"accountTo\" : \"NL04INHO6818968668\",\n  \"amount\" : 123.45,\n  \"performedBy\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"transactionDate\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"accountFrom\" : \"NL01INHO0000579848\",\n  \"transactionId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TransactionList> getTransactionsByUser(@Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return." ,schema=@Schema(allowableValues={  }, minimum="10", maximum="50"
, defaultValue="10")) @Valid @RequestParam(value = "max", required = false, defaultValue="10") Integer max,@Min(0)@Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set." ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "offset", required = false) Integer offset) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TransactionList>(objectMapper.readValue("[ {\n  \"transactionId\" : \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n  \"accountTo\" : \"NL04INHO6818968668\",\n  \"accountFrom\" : \"NL01INHO0000579848\",\n  \"transactionDate\" : \"2021/12/01 16:02:06\",\n  \"amount\" : 123.45,\n  \"performedBy\" : \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n}, {\n  \"transactionId\" : \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n  \"accountTo\" : \"NL04INHO6868186817\",\n  \"accountFrom\" : \"NL01INHO0000579848\",\n  \"transactionDate\" : \"2021/12/01 16:02:06\",\n  \"amount\" : 123.45,\n  \"performedBy\" : \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n}, {\n  \"transactionId\" : \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n  \"accountTo\" : \"NL09INHO6891486186\",\n  \"accountFrom\" : \"NL01INHO0000579848\",\n  \"transactionDate\" : \"2021/12/01 16:02:06\",\n  \"amount\" : 123.45,\n  \"performedBy\" : \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n} ]", TransactionList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TransactionList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TransactionList>(HttpStatus.NOT_IMPLEMENTED);
    }

}
