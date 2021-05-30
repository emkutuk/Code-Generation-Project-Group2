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
 * Deposit
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")


public class Deposit   {
  @JsonProperty("transactionId")
  private UUID transactionId = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("transactionDate")
  private OffsetDateTime transactionDate = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("performedBy")
  private UUID performedBy = null;

  public Deposit transactionId(UUID transactionId) {
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

  public Deposit accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * IBAN of the account receiving the money.
   * @return accountTo
   **/
  @Schema(example = "NL04INHO6818968668", required = true, description = "IBAN of the account receiving the money.")
      @NotNull

    public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public Deposit transactionDate(OffsetDateTime transactionDate) {
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

  public Deposit amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The amonut of the transaction in EURO.
   * minimum: 0
   * @return amount
   **/
  @Schema(example = "123.45", required = true, description = "The amonut of the transaction in EURO.")
      @NotNull

  @DecimalMin("0")  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Deposit performedBy(UUID performedBy) {
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
    Deposit deposit = (Deposit) o;
    return Objects.equals(this.transactionId, deposit.transactionId) &&
        Objects.equals(this.accountTo, deposit.accountTo) &&
        Objects.equals(this.transactionDate, deposit.transactionDate) &&
        Objects.equals(this.amount, deposit.amount) &&
        Objects.equals(this.performedBy, deposit.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, accountTo, transactionDate, amount, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Deposit {\n");
    
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
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
