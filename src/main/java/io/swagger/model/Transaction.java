package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")
@Entity
public class Transaction {
  @Id
  @JsonProperty("transactionId")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
          name = "UUID",
          strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", updatable = false, nullable = false)
  private UUID transactionId = null;

  @Column(name = "accountTo")
  @JsonProperty("accountTo")
  private String accountTo = null;

  @Column(name = "accountFrom")
  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @Column(name = "transactionDate")
  @JsonProperty("transactionDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime transactionDate = null;

  @Column(name = "amount")
  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("performedBy")
  @Column(name = "performedBy")
  private UUID performedBy = null;

  public Transaction() {
  }

  // New Transaction
  public Transaction(String accountTo, String accountFrom, Double amount, UUID performedBy) {
    this.transactionId = UUID.randomUUID();
    this.transactionDate = LocalDateTime.now();
    this.accountTo = accountTo;
    this.accountFrom = accountFrom;
    this.amount = amount;
    this.performedBy = performedBy;
  }

  //New Transaction from db
  public Transaction(UUID transactionId, String accountTo, String accountFrom, LocalDateTime transactionDate, Double amount, UUID performedBy) {
    this.transactionId = transactionId;
    this.accountTo = accountTo;
    this.accountFrom = accountFrom;
    this.transactionDate = transactionDate;
    this.amount = amount;
    this.performedBy = performedBy;
  }

  // Future Transaction
  public Transaction(String accountTo, String accountFrom, LocalDateTime transactionDate, Double amount, UUID performedBy) {
    this.transactionId = UUID.randomUUID();
    this.accountTo = accountTo;
    this.accountFrom = accountFrom;
    this.transactionDate = transactionDate;
    this.amount = amount;
    this.performedBy = performedBy;
  }

  /**
   * The ID of the created Transaction.
   *
   * @return transactionId
   **/
  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "The ID of the created Transaction.")
  @Valid
  public UUID getTransactionId() {
    return transactionId;
  }

  public Transaction accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * IBAN of the account receiving the money.
   *
   * @return accountTo
   **/
  @Schema(example = "NL04INHO6818968668", required = true, description = "IBAN of the account receiving the money.")
  @NotNull
  @Size(max = 34)
  public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public Transaction accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * IBAN of the account sending the money.
   *
   * @return accountFrom
   **/

  @Schema(example = "NL01INHO0000579848", required = true, description = "IBAN of the account sending the money.")
  @NotNull
  @Size(max = 34)
  public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Transaction transactionDate(LocalDateTime transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Timestamp of the transaction.
   *
   * @return transactionDate
   **/
  @Schema(description = "Timestamp of the transaction.")
  @Valid
  public LocalDateTime getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Transaction amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The amonut of the transaction in EURO.
   * minimum: 0
   *
   * @return amount
   **/
  @Schema(example = "123.45", required = true, description = "The amonut of the transaction in EURO.")
  @NotNull
  @DecimalMin("0")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Transaction performedBy(UUID performedBy) {
    this.performedBy = performedBy;
    return this;
  }

  /**
   * ID of the user performing the transaction.
   *
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
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.transactionId, transaction.transactionId) &&
            Objects.equals(this.accountTo, transaction.accountTo) &&
            Objects.equals(this.accountFrom, transaction.accountFrom) &&
            Objects.equals(this.transactionDate, transaction.transactionDate) &&
            Objects.equals(this.amount, transaction.amount) &&
            Objects.equals(this.performedBy, transaction.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, accountTo, accountFrom, transactionDate, amount, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");

    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
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
