package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import io.swagger.security.Role;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsersApiControllerTest
{/*
    @Autowired
    private MockMvc mvc;

    @Autowired
    UserService userService;

    private String employeeToken;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() throws Exception
    {
        mapper = new ObjectMapper();
        LoginDto employee = new LoginDto("employee", "employee");
        employeeToken = this.mvc.perform(post("/Login").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(employee))).andReturn().getResponse().getContentAsString();
    }

    @Test
    void deleteUserById() throws Exception
    {
        User user = userService.getUserByEmail("customer");
        this.mvc.perform(MockMvcRequestBuilders.delete("/Users/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + employeeToken).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception
    {
        this.mvc.perform(get("/Users?offset=0&&,ax=50").header("authorization", "Bearer " + employeeToken)).andExpect(status().isOk());

    }

    @Test
    void loginUser() throws Exception
    {
        LoginDto customerDTO = new LoginDto("customer", "customer");
        this.mvc.perform(post("/Login").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(customerDTO))).andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception
    {
        User testUser = new User("firstNameTest", "lastNameTest", "3100000000", "emailTest", "passwordTest", new ArrayList<>(), Role.ROLE_CUSTOMER, AccountStatus.ACTIVE);
        this.mvc.perform(post("/Users").contentType(MediaType.APPLICATION_JSON_VALUE).header("authorization", "Bearer " + employeeToken).content(mapper.writeValueAsString(testUser))).andExpect(status().isCreated());
    }

    @Test
    void getUserById() throws Exception
    {
        User user = userService.getUserByEmail("customer");

        this.mvc.perform(get("/Users/" + user.getId().toString())
                .header("authorization", "Bearer " + employeeToken))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserById()
    {
    }*/
}