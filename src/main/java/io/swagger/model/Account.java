package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-30T19:31:26.554Z[GMT]")

@Entity
public class Account   {
  @JsonProperty("id")
  @Id
  @GeneratedValue
  private Long id = null;

  @Column(name = "iban")
  @JsonProperty("iban")
  private String iban = null;

  /**
   * The type of the account. This can be either saving or current.
   */
  public enum AccountTypeEnum {
    SAVING("saving"),
    
    CURRENT("current");

    private String value;

    AccountTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccountTypeEnum fromValue(String text) {
      for (AccountTypeEnum b : AccountTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("accountType")
  private AccountTypeEnum accountType = AccountTypeEnum.CURRENT;

  @JsonProperty("balance")
  private Double balance = 0d;

  public Account id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * The unique ID of the account.
   * minimum: 1
   * @return id
   **/
  @Schema(example = "3", required = true, description = "The unique ID of the account.")
      @NotNull

  @Min(1L)  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * IBAN number of the account.
   * @return iban
   **/
  @Schema(example = "NL03INHO0033576852", required = true, description = "IBAN number of the account.")
      @NotNull

  @Size(max=34)   public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public Account accountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * The type of the account. This can be either saving or current.
   * @return accountType
   **/
  @Schema(required = true, description = "The type of the account. This can be either saving or current.")
      @NotNull

    public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public Account balance(Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * minimum: 0
   * @return balance
   **/
  @Schema(required = true, description = "")
      @NotNull

  @DecimalMin("0")  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.id, account.id) &&
        Objects.equals(this.iban, account.iban) &&
        Objects.equals(this.accountType, account.accountType) &&
        Objects.equals(this.balance, account.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, iban, accountType, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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
  public Account()
  {
  }
  public Account(String iban, AccountTypeEnum accountType, Double balance)
  {
    this.iban = iban;
    this.accountType = accountType;
    this.balance = balance;
  }
}
