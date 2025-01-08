package net.abid.accountservice.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import net.abid.accountservice.enums.AccountType;
import net.abid.accountservice.model.CustomerModel;

import java.time.LocalDate;

@Entity
@Schema (description = "Account model")
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createDate;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Transient
    private CustomerModel customer;
    private Long customerId;

    public BankAccount() {
        super();
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public CustomerModel getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}