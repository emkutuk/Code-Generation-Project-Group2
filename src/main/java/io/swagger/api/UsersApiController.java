package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.BearerTokenDto;
import io.swagger.model.LoginDto;
import io.swagger.model.User;
import io.swagger.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-06T11:20:30.422Z[GMT]")
@RestController
public class UsersApiController implements UsersApi
{

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request)
    {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody User user)
    {
        String accept = request.getHeader("Accept");

        // Validate user

        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                log.info("Trying to create user");
                userService.register(user.getEmail(), user.getPassword(), user.getRole());
                return new ResponseEntity<User>(HttpStatus.CREATED);
            } catch (Exception e)
            {
                e.printStackTrace();
                // return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> deleteUserById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id)
    {
        String accept = request.getHeader("Accept");

        // Validate user
        log.info("Change user status to disabled");
        userService.deleteUserById(UUID.fromString(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getAllUsers(@Min(0) @Parameter(in = ParameterIn.QUERY, description = "The number of items to skip before starting to collect the result set.", schema = @Schema(allowableValues = {})) @Valid @RequestParam(value = "offset", required = false) Integer offset, @Min(10) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The maximum number of items to return.", schema = @Schema(allowableValues = {}, minimum = "10", maximum = "50", defaultValue = "10")) @Valid @RequestParam(value = "max", required = false, defaultValue = "10") Integer max)
    {
        String accept = request.getHeader("Accept");
        // Validate user
        // Employee Only

        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                log.info("Returning all users");
                return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
            } catch (Exception e)
            {
                // return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<User> getUserById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id)
    {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                log.info("Getting user by ID");
                return new ResponseEntity<User>(userService.getUserById(UUID.fromString(id)), HttpStatus.OK);
            } catch (Exception e)
            {
                //return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/Login")
    public ResponseEntity<String> loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody LoginDto loginDto)
    {
        String accept = request.getHeader("Accept");

        try
        {

            return new ResponseEntity<String>(userService.login(loginDto.getEmailAddress(), loginDto.getPassword()), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<User> updateUserById(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody User user)
    {

        String accept = request.getHeader("Accept");
        // Validate User

        if (accept != null && accept.contains("application/json"))
        {
            try
            {
                log.info("Updating a user");
                return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e)
            {
                // return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }
}
