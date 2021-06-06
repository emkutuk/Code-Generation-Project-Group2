package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.BasicTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Withdrawal
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-06T11:20:30.422Z[GMT]")


public class Withdrawal extends BasicTransaction implements AnyOfinlineResponse2001 {
  @JsonProperty("accountFrom")
  private String accountFrom = null;

  public Withdrawal accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * IBAN of the account withdrawing the money.
   * @return accountFrom
   **/
  @Schema(example = "NL04INHO6818968668", required = true, description = "IBAN of the account withdrawing the money.")
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
    Withdrawal withdrawal = (Withdrawal) o;
    return Objects.equals(this.accountFrom, withdrawal.accountFrom) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountFrom, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Withdrawal {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
