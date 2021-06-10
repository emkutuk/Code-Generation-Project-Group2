/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-10T11:32:06.118Z[GMT]")
@Validated
public interface AccountsApi {

    @Operation(summary = "Adds a new Account", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The new account has been created.", content = @Content(schema = @Schema(implementation = Account.class))) })
    @RequestMapping(value = "/Accounts",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Account> addANewAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Account body);


    @Operation(summary = "Change an Account Status", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The account status has been changed.", content = @Content(schema = @Schema(implementation = Account.class))) })
    @RequestMapping(value = "/Accounts/{iban}/Status/{status}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Account> changeAccountStatus(@Size(max=34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.PATH, description = "Account status to be changed to.", required=true, schema=@Schema(allowableValues={ "active", "closed" }
)) @PathVariable("status") String status);


    @Operation(summary = "Changes account type.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The account type has been changed.") })
    @RequestMapping(value = "/Accounts/{iban}/{type}",
        method = RequestMethod.POST)
    ResponseEntity<Void> changeAccountType(@Size(max=34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.PATH, description = "The new type for the account to be changed into.", required=true, schema=@Schema(allowableValues={ "saving", "current" }
)) @PathVariable("type") String type);


    @Operation(summary = "Gets an account balance by Iban.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Balance of the account has been returned.") })
    @RequestMapping(value = "/Accounts/{iban}/Balance",
        method = RequestMethod.GET)
    ResponseEntity<Void> getAccountBalanceByIban(@Size(max=34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets an account by Iban.", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "An account object has been returned.", content = @Content(schema = @Schema(implementation = Account.class))) })
    @RequestMapping(value = "/Accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIban(@Size(max=34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets all accounts", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Paginated accounts have been returned") })
    @RequestMapping(value = "/Accounts",
        method = RequestMethod.GET)
    ResponseEntity<Void> getAllAccounts(@Min(0)@Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set." ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "offset", required = false) Integer offset, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return." ,schema=@Schema(allowableValues={  }, minimum="10", maximum="50"
, defaultValue="10")) @Valid @RequestParam(value = "max", required = false, defaultValue="10") Integer max);


    @Operation(summary = "Updates an existing account", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "An account object has been updated.") })
    @RequestMapping(value = "/Accounts/{iban}",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateAccountByIban(@Size(max=34) @Parameter(in = ParameterIn.PATH, description = "The account to perform the action on.", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Account body);

}

