package com.backend.azkivam.accounts.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TransferFund {
    @NotBlank
    @Size(min = 1, max = 64)
    private String accountNumber;

    @NotBlank
    @Size(min = 1, max = 64)
    private String targetAccountNumber;

    @NotBlank
    @Positive(message = "Amount must be a positive number")
    private Double amount;

    public TransferFund(String accountNumber, String targetAccountNumber, Double amount) {
        this.accountNumber = accountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
    }

    public @NotBlank @Size(min = 1, max = 64) String getAccountNumber() {
        return accountNumber;
    }

    public @NotBlank @Size(min = 1, max = 64) String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public @NotBlank @Positive(message = "Amount must be a positive number") Double getAmount() {
        return amount;
    }
}
