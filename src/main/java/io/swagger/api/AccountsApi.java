/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")
@Validated
public interface AccountsApi {

    @Operation(summary = "Adds a new Account", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountManagement" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The new account has been created.", content = @Content(schema = @Schema(implementation = Account.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Accounts",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Account> addANewAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Account body);


    @Operation(summary = "Changes account type.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountType" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The account type has been changed."),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Accounts/{iban}/{type}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> changeAccountType(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.PATH, description = "The new type for the account to be changed into.", required=true, schema=@Schema(allowableValues={ "saving", "current" }
)) @PathVariable("type") String type);


    @Operation(summary = "Deletes an account by Iban", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountManagement" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The account object has been deleted."),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets an account balance by Iban.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountBalance" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Balance of the account has been returned."),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Accounts/{iban}/getBalance",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> getAccountBalanceByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets an account by Iban.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountManagement" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "An account object has been returned.", content = @Content(schema = @Schema(implementation = Account.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets all accounts", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountManagement" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Paginated accounts have been returned") })
    @RequestMapping(value = "/Accounts",
        method = RequestMethod.GET)
    ResponseEntity<Void> getAllAccounts(@Min(0)@Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set." ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "offset", required = false) Integer offset, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return." ,schema=@Schema(allowableValues={  }, minimum="10", maximum="50"
, defaultValue="10")) @Valid @RequestParam(value = "max", required = false, defaultValue="10") Integer max);


    @Operation(summary = "Updates an existing account", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "AccountManagement" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "An account object has been updated."),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Accounts/{iban}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Account Iban", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Account body);

}

