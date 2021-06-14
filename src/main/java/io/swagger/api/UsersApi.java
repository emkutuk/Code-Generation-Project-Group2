/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.BearerTokenDto;
import io.swagger.model.LoginDto;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-06-06T11:20:30.422Z[GMT]")
@Validated
public interface UsersApi {

  @Operation(
      summary = "Registers a user.",
      description = "Register a user to the system.",
      security = {@SecurityRequirement(name = "bearerAuth")},
      tags = {"User"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "The user has been created.",
            content = @Content(schema = @Schema(implementation = User.class)))
      })
  @RequestMapping(
      value = "/Users",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<User> createUser(
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema())
          @Valid
          @RequestBody
          User user);

  @Operation(
      summary = "Delete a user.",
      description = "Change user accountStatus to Inactive.",
      security = {@SecurityRequirement(name = "bearerAuth")},
      tags = {"User"})
  @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "User set as inactive.")})
  @RequestMapping(value = "/Users/{id}", method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteUserById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id);

  @Operation(
      summary = "Get all users registered on the system.",
      description = "Fetches the entire list of users stored on the system.",
      security = {@SecurityRequirement(name = "bearerAuth")},
      tags = {"User"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Returns a list of users",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
      })
  @RequestMapping(
      value = "/Users",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<User>> getAllUsers(
      @Min(0)
          @Parameter(
              in = ParameterIn.QUERY,
              description =
                  "The number of items to skip before starting to collect the result set.",
              schema = @Schema(allowableValues = {}))
          @Valid
          @RequestParam(value = "offset", required = false)
          Integer offset,
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
          Integer max);

  @Operation(
      summary = "Get a user by their ID.",
      description = "Fetches a user from the system by the ID provided.",
      security = {@SecurityRequirement(name = "bearerAuth")},
      tags = {"User"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Succesful",
            content = @Content(schema = @Schema(implementation = User.class)))
      })
  @RequestMapping(
      value = "/Users/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<User> getUserById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id);

  @Operation(
      summary = "User login.",
      description = "login",
      tags = {"User"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "User logged in.",
            content = @Content(schema = @Schema(implementation = LoginDto.class)))
      })
  @RequestMapping(
      value = "/Users/Login/",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<String> loginUser(
      @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody
          LoginDto loginDto);

  @Operation(
      summary = "Update a user.",
      description = "User(customer - employee) can update their information.",
      security = {@SecurityRequirement(name = "bearerAuth")},
      tags = {"User"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Information has been updated.",
            content = @Content(schema = @Schema(implementation = BearerTokenDto.class)))
      })
  @RequestMapping(
      value = "/Users/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<User> updateUserById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id,
      @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody
          User user);
}
