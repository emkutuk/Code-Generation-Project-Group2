package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** User */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-06-06T11:20:30.422Z[GMT]")
@Data
@Entity
public class User {
  @JsonProperty("id")
  @Id
  @GeneratedValue
  // @OneToOne(targetEntity = Transaction.class)
  private UUID id = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("accounts")
  @Valid
  @OneToMany(mappedBy = "userId", targetEntity = Account.class)
  private List<Account> accounts = null;

  @JsonProperty("role")
  private Role role = Role.CUSTOMER;

  @JsonProperty("accountStatus")
  private AccountStatus accountStatus = AccountStatus.ACTIVE;

  public User id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(required = true, accessMode = Schema.AccessMode.READ_ONLY, description = "")
  @NotNull
  @Valid
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   *
   * @return firstName
   */
  @Schema(example = "Rob", required = true, description = "")
  @NotNull
  @Size(max = 100)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   *
   * @return lastName
   */
  @Schema(example = "Banks", required = true, description = "")
  @NotNull
  @Size(max = 100)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   *
   * @return phoneNumber
   */
  @Schema(example = "321123527", required = true, description = "")
  @NotNull
  @Size(max = 20)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   *
   * @return email
   */
  @Schema(example = "rbanks@gmail.com", required = true, description = "")
  @NotNull
  @Size(max = 256)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   *
   * @return password
   */
  @Schema(example = "GetMyP@ss1", required = true, description = "")
  @NotNull
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User accounts(List<Account> accounts) {
    this.accounts = accounts;
    return this;
  }

  public User addAccountsItem(Account accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<Account>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

  /**
   * Get accounts
   *
   * @return accounts
   */
  @Schema(
      example =
          "[{\"id\":3,\"iban\":\"NL03INHO0033576852\",\"accountType\":\"current\",\"balance\":320},{\"id\":3,\"iban\":\"NL03INHO0033576883\",\"accountType\":\"savings\",\"balance\":800}]",
      accessMode = Schema.AccessMode.READ_ONLY,
      description = "")
  @Valid
  @Size(max = 2)
  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public User role(Role role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   *
   * @return role
   */
  @Schema(description = "")
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public User accountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
    return this;
  }

  /**
   * Get accountStatus
   *
   * @return accountStatus
   */
  @Schema(description = "")
  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
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
