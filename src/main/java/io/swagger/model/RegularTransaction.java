package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** RegularTransaction */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-06-06T11:20:30.422Z[GMT]")
@Data
@Entity
public class RegularTransaction extends Transaction {
  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  /**
   * IBAN of the account receiving the money.
   *
   * @return accountTo
   */
  @Schema(
      example = "NL04INHO6818968668",
      required = true,
      description = "IBAN of the account receiving the money.")
  @NotNull
  @Size(max = 34)
  public String getAccountTo() {
    return accountTo;
  }

  /**
   * IBAN of the account sending the money.
   *
   * @return accountFrom
   */
  @Schema(
      example = "NL01INHO0000579848",
      required = true,
      description = "IBAN of the account sending the money.")
  @NotNull
  @Size(max = 34)
  public String getAccountFrom() {
    return accountFrom;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
