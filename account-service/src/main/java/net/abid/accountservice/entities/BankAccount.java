package net.abid.accountservice.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import net.abid.accountservice.enums.AccountType;
import net.abid.accountservice.model.CustomerModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Schema (description = "Account model")
@EntityListeners(AuditingEntityListener.class)
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate updatedDate;
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
        return createdDate;
    }
    public void setCreateDate(LocalDate createDate) {
        this.createdDate = createDate;
    }
    public LocalDate getUpdatedDate(){
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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