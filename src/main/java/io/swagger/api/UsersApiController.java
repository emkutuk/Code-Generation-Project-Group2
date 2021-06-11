package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.BearerTokenDto;
import io.swagger.model.LoginDto;
import io.swagger.model.User;
import io.swagger.security.TokenAuthenticate;
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
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-06-06T11:20:30.422Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

  private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  @Autowired private UserService userService;

  @org.springframework.beans.factory.annotation.Autowired
  public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<User> createUser(
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema())
          @Valid
          @RequestBody
          User user) {
    String accept = request.getHeader("Accept");

    // Validate user

    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
      } catch (Exception e) {
        e.printStackTrace();
        // return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<Void> deleteUserById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id) {
    String accept = request.getHeader("Accept");

    // Validate user
    userService.deleteUserById(UUID.fromString(id));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<List<User>> getAllUsers(
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
          Integer max) {
    String accept = request.getHeader("Accept");
    // Validate user

    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
      } catch (Exception e) {
        // return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<User> getUserById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<User>(userService.getUserById(UUID.fromString(id)), HttpStatus.OK);
      } catch (Exception e) {
        //return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<BearerTokenDto> loginUser(
      @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody
          LoginDto loginDto) {
    String accept = request.getHeader("Accept");

    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<BearerTokenDto>(TokenAuthenticate.getToken(loginDto), HttpStatus.OK);
      } catch (Exception e)
      {
        // return new ResponseEntity<BearerTokenDto>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<BearerTokenDto>(HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<User> updateUserById(
      @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
          @PathVariable("id")
          String id,
      @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody
          User user) {

    String accept = request.getHeader("Accept");
    // Validate User

    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<User>(userService.updateUser(user) , HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        // return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
  }
}
