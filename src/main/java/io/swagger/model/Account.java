package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-06T11:20:30.422Z[GMT]")

@Entity
public class Account   {
  @JsonProperty("id")
  @Id
  @GeneratedValue
  private Long id = null;

  @Column(name = "iban")
  @JsonProperty("iban")
  private String iban = null;

  //Its here to prevent no default constructor error
  public Account()
  {
  }

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
  @Column(name = "accountType")
  @JsonProperty("accountType")
  private AccountTypeEnum accountType = AccountTypeEnum.CURRENT;

  /**
   * The status of the account. This can be either active or closed.
   */
  public enum AccountStatusEnum {
    ACTIVE("active"),
    
    CLOSED("closed");

    private String value;

    AccountStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccountStatusEnum fromValue(String text) {
      for (AccountStatusEnum b : AccountStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @Column(name = "accountStatus")
  @JsonProperty("accountStatus")
  private AccountStatusEnum accountStatus = AccountStatusEnum.ACTIVE;

  @Column(name = "balance")
  @JsonProperty("balance")
  private Double balance = 0d;

  @Column(name = "transactions")
  @OneToMany(targetEntity=BasicTransaction.class, mappedBy="transactionId")
  @JsonProperty("transactions")
  @Valid
  private List<BasicTransaction> transactions = null;

  @Column(name = "userId")
  @JsonProperty("userId")
  private UUID userId = null;

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

  public Account accountStatus(AccountStatusEnum accountStatus) {
    this.accountStatus = accountStatus;
    return this;
  }

  /**
   * The status of the account. This can be either active or closed.
   * @return accountStatus
   **/
  @Schema(required = true, description = "The status of the account. This can be either active or closed.")
      @NotNull

    public AccountStatusEnum getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatusEnum accountStatus) {
    this.accountStatus = accountStatus;
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

  public Account transactions(List<BasicTransaction> transactions) {
    this.transactions = transactions;
    return this;
  }

  public Account addTransactionsItem(BasicTransaction transactionsItem) {
    if (this.transactions == null) {
      this.transactions = new ArrayList<BasicTransaction>();
    }
    this.transactions.add(transactionsItem);
    return this;
  }

  /**
   * All the transactions that belongs to the account
   * @return transactions
   **/
  @Schema(description = "All the transactions that belongs to the account")
  
    public List<BasicTransaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<BasicTransaction> transactions) {
    this.transactions = transactions;
  }

  public Account userId(UUID userId) {
    this.userId = userId;
    return this;
  }

  /**
   * The id of the account's owner user
   * @return userId
   **/
  @Schema(required = true, description = "The id of the account's owner user")
      @NotNull

    @Valid
    public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
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
        Objects.equals(this.accountStatus, account.accountStatus) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.transactions, account.transactions) &&
        Objects.equals(this.userId, account.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, iban, accountType, accountStatus, balance, transactions, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    accountStatus: ").append(toIndentedString(accountStatus)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

  public Account(AccountTypeEnum accountType) throws Exception
  {
    this.iban = "";
    this.accountType = accountType;
    this.balance = 0.0;
  }

  //These constructors are for testing
  public Account(AccountTypeEnum accountType, Double balance) throws Exception
  {
    this.iban = "";
    this.accountType = accountType;
    this.balance = balance;
  }


  public Account(Long id, String iban, AccountTypeEnum accountType, Double balance)
  {
    this.id = id;
    this.iban = iban;
    this.accountType = accountType;
    this.balance = balance;
  }
}
