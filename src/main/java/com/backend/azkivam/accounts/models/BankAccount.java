package com.backend.azkivam.accounts.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 64)
    @Column(name = "account_number", unique = true)
    private String accountNumber;


    @NotBlank
    @Size(min = 4, max = 64)
    @Column(name = "account_name")
    private String accountHolderName;


    @Positive(message = "Balance must be a positive number")
    @Column(name = "balance")
    private Double balance = 1000.0;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    public @NotBlank @Size(min = 1, max = 64) String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotBlank @Size(min = 1, max = 64) String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotBlank @Size(min = 4, max = 64) String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(@NotBlank @Size(min = 4, max = 64) String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public @Positive(message = "Balance must be a positive number") Double getBalance() {
        return balance;
    }

    public void setBalance(@Positive(message = "Balance must be a positive number") Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(accountHolderName, that.accountHolderName) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, accountHolderName, balance);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
