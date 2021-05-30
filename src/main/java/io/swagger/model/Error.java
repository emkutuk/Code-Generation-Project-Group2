package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Error
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")


public class Error   {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("error")
  private String error = null;

  @JsonProperty("description")
  private String description = null;

  public Error code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * minimum: 400
   * maximum: 600
   * @return code
   **/
  @Schema(example = "404", description = "")
  
  @Min(400) @Max(600)   public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Error error(String error) {
    this.error = error;
    return this;
  }

  /**
   * Get error
   * @return error
   **/
  @Schema(example = "Not Found.", description = "")
  
  @Size(max=100)   public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Error description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "The requested resource was not found on the server.", description = "")
  
  @Size(max=500)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.error, error.error) &&
        Objects.equals(this.description, error.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, error, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
