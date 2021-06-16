package io.swagger.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-10T11:32:06.118Z[GMT]")

@Entity
public class Account
{
    @Id
    @JsonProperty("iban")
    private String iban = null;

    @JsonProperty("accountType")
    private AccountType accountType = AccountType.CURRENT;

    @JsonProperty("accountStatus")
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @JsonProperty("balance")
    private Double balance = 0d;

    @JsonProperty("transactions")
    @OneToMany(mappedBy = "transactionId")
    @JsonManagedReference
    private List<Transaction> transactions = null;

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public Account iban(String iban)
    {
        this.iban = iban;
        return this;
    }

    /**
     * IBAN number of the account.
     *
     * @return iban
     **/
    @Schema(example = "NL03INHO0033576852", required = true, description = "IBAN number of the account.")
    @NotNull

    @Size(max = 34)
    public String getIban()
    {
        return iban;
    }

    public void setIban(String iban)
    {
        this.iban = iban;
    }

    public Account accountType(AccountType accountType)
    {
        this.accountType = accountType;
        return this;
    }

    /**
     * The type of the account. This can be either saving or current.
     *
     * @return accountType
     **/
    @Schema(required = true, description = "The type of the account. This can be either saving or current.")
    @NotNull

    public AccountType getAccountType()
    {
        return accountType;
    }

    public void setAccountType(AccountType accountType)
    {
        this.accountType = accountType;
    }

    public Account accountStatus(AccountStatus accountStatus)
    {
        this.accountStatus = accountStatus;
        return this;
    }

    /**
     * The status of the account. This can be either active or closed.
     *
     * @return accountStatus
     **/
    @Schema(required = true, description = "The status of the account. This can be either active or closed.")
    @NotNull

    public AccountStatus getAccountStatus()
    {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus)
    {
        this.accountStatus = accountStatus;
    }

    public Account balance(Double balance)
    {
        this.balance = balance;
        return this;
    }

    /**
     * Get balance
     * minimum: 0
     *
     * @return balance
     **/
    @Schema(required = true, description = "")
    @NotNull

    @DecimalMin("0")
    public Double getBalance()
    {
        return balance;
    }

    public void setBalance(Double balance)
    {
        this.balance = balance;
    }


    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(this.iban, account.iban) && Objects.equals(this.accountType, account.accountType) && Objects.equals(this.accountStatus, account.accountStatus) && Objects.equals(this.balance, account.balance);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(iban, accountType, accountStatus, balance);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");

        sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
        sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
        sb.append("    accountStatus: ").append(toIndentedString(accountStatus)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o)
    {
        if (o == null)
        {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    //Its here to prevent no default constructor error
    public Account()
    {

    }

    public Account(AccountType accountType) throws Exception
    {
        this.iban = "";
        this.accountType = accountType;
        this.balance = 0.0;
    }

    //These constructors are for testing
    public Account(AccountType accountType, Double balance) throws Exception
    {
        this.iban = "";
        this.accountType = accountType;
        this.balance = balance;
    }


    public Account(String iban, AccountType accountType, Double balance)
    {
        this.iban = iban;
        this.accountType = accountType;
        this.balance = balance;
    }

}
