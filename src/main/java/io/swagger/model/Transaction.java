package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/** Transaction */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-06-06T11:20:30.422Z[GMT]")
@Entity
@Data
@NoArgsConstructor
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Transaction{
  @Transient // Don't save to DB
  @JsonIgnore
  private static final DateTimeFormatter dateTimeFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Id
  @JsonProperty("transactionId")
  //@ManyToOne(targetEntity = Account.class)
  //@JsonBackReference
  @GeneratedValue
  private UUID transactionId = null;

  @JsonProperty("transactionDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime transactionDate = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("performedBy")
  private UUID performedBy = null;

  public Transaction(Double amount, UUID performedBy, LocalDateTime transactionDate)
  {
    this.amount = amount;
    this.performedBy = performedBy;
    this.transactionDate = transactionDate != null ? transactionDate : LocalDateTime.now();
  }

  /**
   * The ID of the created Transaction.
   *
   * @return transactionId
   */
  @Schema(
      accessMode = Schema.AccessMode.READ_ONLY,
      description = "The ID of the created Transaction.")
  @Valid
  public UUID getTransactionId() {
    return transactionId;
  }

  /**
   * Timestamp of the transaction.
   *
   * @return transactionDate
   */

  @Schema(example = "2021-12-01 16:02:06", description = "Timestamp of the transaction.")
  public LocalDateTime getTransactionDate() {
    return this.transactionDate;
  }

  public void setTransactionDate(LocalDateTime date) {
    this.transactionDate = date;
  }

  /**
   * The amonut of the transaction in EURO. minimum: 0
   *
   * @return amount
   */
  @Schema(
      example = "123.45",
      required = true,
      description = "The amonut of the transaction in EURO.")
  @NotNull
  @DecimalMin("0")
  public Double getAmount() {
    return amount;
  }

  /**
   * ID of the user performing the transaction.
   *
   * @return performedBy
   */
  @Schema(
      required = true,
      accessMode = Schema.AccessMode.READ_ONLY,
      description = "ID of the user performing the transaction.")
  @NotNull
  @Valid
  public UUID getPerformedBy() {
    return performedBy;
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
