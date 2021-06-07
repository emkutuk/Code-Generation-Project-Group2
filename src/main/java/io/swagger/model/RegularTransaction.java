package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * RegularTransaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-06T11:20:30.422Z[GMT]")


public class RegularTransaction extends BasicTransaction {
  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  public RegularTransaction accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * IBAN of the account receiving the money.
   * @return accountTo
   **/
  @Schema(example = "NL04INHO6818968668", required = true, description = "IBAN of the account receiving the money.")
      @NotNull

  @Size(max=34)   public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public RegularTransaction accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * IBAN of the account sending the money.
   * @return accountFrom
   **/
  @Schema(example = "NL01INHO0000579848", required = true, description = "IBAN of the account sending the money.")
      @NotNull

  @Size(max=34)   public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegularTransaction regularTransaction = (RegularTransaction) o;
    return Objects.equals(this.accountTo, regularTransaction.accountTo) &&
        Objects.equals(this.accountFrom, regularTransaction.accountFrom) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountTo, accountFrom, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegularTransaction {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
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
