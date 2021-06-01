package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Withdrawal
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")


public class Withdrawal   {
  @JsonProperty("transactionId")
  private UUID transactionId = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("transactionDate")
  private OffsetDateTime transactionDate = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("performedBy")
  private UUID performedBy = null;

  public Withdrawal transactionId(UUID transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * The ID of the created Transaction.
   * @return transactionId
   **/
  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "The ID of the created Transaction.")
  
    @Valid
    public UUID getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(UUID transactionId) {
    this.transactionId = transactionId;
  }

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

  public Withdrawal transactionDate(OffsetDateTime transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Timestamp of the transaction.
   * @return transactionDate
   **/
  @Schema(required = true, description = "Timestamp of the transaction.")
      @NotNull

    @Valid
    public OffsetDateTime getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(OffsetDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Withdrawal amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The amount of the transaction in EURO.
   * @return amount
   **/
  @Schema(example = "123.45", required = true, description = "The amount of the transaction in EURO.")
      @NotNull

    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Withdrawal performedBy(UUID performedBy) {
    this.performedBy = performedBy;
    return this;
  }

  /**
   * ID of the user performing the transaction.
   * @return performedBy
   **/
  @Schema(required = true, accessMode = Schema.AccessMode.READ_ONLY, description = "ID of the user performing the transaction.")
      @NotNull

    @Valid
    public UUID getPerformedBy() {
    return performedBy;
  }

  public void setPerformedBy(UUID performedBy) {
    this.performedBy = performedBy;
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
    return Objects.equals(this.transactionId, withdrawal.transactionId) &&
        Objects.equals(this.accountFrom, withdrawal.accountFrom) &&
        Objects.equals(this.transactionDate, withdrawal.transactionDate) &&
        Objects.equals(this.amount, withdrawal.amount) &&
        Objects.equals(this.performedBy, withdrawal.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, accountFrom, transactionDate, amount, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Withdrawal {\n");
    
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    performedBy: ").append(toIndentedString(performedBy)).append("\n");
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