package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role
{
  EMPLOYEE("employee"),

  CUSTOMER("customer");

  private String value;


  Role(String role)
  {
    this.value = role;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Role fromValue(String text) {
    for (Role role : Role.values()) {
      if (String.valueOf(role.value).equals(text)) {
        return role;
      }
    }
    return null;
  }

}