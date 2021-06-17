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
}
