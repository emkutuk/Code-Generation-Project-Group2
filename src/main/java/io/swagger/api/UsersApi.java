/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.User;
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
public interface UsersApi {

    @Operation(summary = "Get all users registered on the system.", description = "Fetches the entire list of users stored on the system.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "users returned"),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> getAllUsers(@Min(0)@Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set." ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "offset", required = false) Integer offset, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return." ,schema=@Schema(allowableValues={  }, minimum="10", maximum="50"
, defaultValue="10")) @Valid @RequestParam(value = "max", required = false, defaultValue="10") Integer max);


    @Operation(summary = "Get a user by their ID.", description = "Fetches a user from the system by the ID provided.", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "succesful", content = @Content(schema = @Schema(implementation = User.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad Reqeust. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "401", description = "The entered credentials are invalid or incorrect. Please try again.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "You do not have access to perform this action. Forbidden.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "Not Found.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "429", description = "You have sent too many requests. Please try again in at least 300 seconds.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "200", description = "An unknown error has occured.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/Users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> getUserById(@Parameter(in = ParameterIn.PATH, description = "user id", required=true, schema=@Schema()) @PathVariable("id") Integer id);

}
