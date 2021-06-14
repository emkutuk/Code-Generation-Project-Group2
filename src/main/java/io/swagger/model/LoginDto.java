package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class LoginDto {
    @JsonProperty("email")
    private String emailAddress = null;
    @JsonProperty("password")
    private String password = null;

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
