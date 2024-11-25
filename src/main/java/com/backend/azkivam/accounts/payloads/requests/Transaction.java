package com.backend.azkivam.accounts.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Transaction {
    @NotBlank
    @Size(min = 1, max = 64)
    private String accountNumber;

    @NotBlank
    @Positive(message = "Amount must be a positive number")
    private Double amount;

    public @NotBlank @Size(min = 1, max = 64) String getAccountNumber() {
        return accountNumber;
    }

    public @NotBlank @Positive(message = "Amount must be a positive number") Double getAmount() {
        return amount;
    }
}
