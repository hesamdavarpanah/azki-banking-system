package com.backend.azkivam.accounts.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAccountBank {
    @NotBlank
    @Size(min = 1, max = 64)
    private String accountNumber;

    @NotBlank
    @Size(min = 4, max = 64)
    private String accountHolderName;

    public @NotBlank @Size(min = 1, max = 64) String getAccountNumber() {
        return accountNumber;
    }

    public @NotBlank @Size(min = 4, max = 64) String getAccountHolderName() {
        return accountHolderName;
    }
}
