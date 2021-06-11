package io.swagger.security;

import io.swagger.model.BearerTokenDto;
import io.swagger.model.LoginDto;
import io.swagger.model.User;

public class TokenAuthenticate
{
  public static BearerTokenDto getToken(LoginDto loginDto)
  {
    // Compare hashed passwords
    // Generate token
    // Return token

    BearerTokenDto dto = new BearerTokenDto();
    dto.setBearerToken("token-test");
    return dto;
  }

  public static User getUserFromToken(String token)
  {
    // Validate token vs user
    // return user

    return new User();
  }
}
