package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

import lombok.Generated;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BasicTransaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-06T11:20:30.422Z[GMT]")

@Entity
@MappedSuperclass
public class BasicTransaction
{
  @Id
  @JsonProperty("transactionId")
  private UUID transactionId = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("performedBy")
  private UUID performedBy = null;

  public BasicTransaction transactionId(UUID transactionId) {
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

  public BasicTransaction transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Timestamp of the transaction.
   * @return transactionDate
   **/
  @Schema(example = "2021/12/01 16:02:06", description = "Timestamp of the transaction.")
  
    public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public BasicTransaction amount(Double amount) {
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

  public BasicTransaction performedBy(UUID performedBy) {
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
    BasicTransaction basicTransaction = (BasicTransaction) o;
    return Objects.equals(this.transactionId, basicTransaction.transactionId) &&
        Objects.equals(this.transactionDate, basicTransaction.transactionDate) &&
        Objects.equals(this.amount, basicTransaction.amount) &&
        Objects.equals(this.performedBy, basicTransaction.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, transactionDate, amount, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BasicTransaction {\n");
    
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
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
