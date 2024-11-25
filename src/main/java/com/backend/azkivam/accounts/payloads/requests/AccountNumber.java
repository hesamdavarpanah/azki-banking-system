package com.backend.azkivam.accounts.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountNumber {
    @Size(min = 1, max = 64)
    @NotBlank
    private String accountNumber;

    public AccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotBlank String getAccountNumber() {
        return accountNumber;
    }
}
