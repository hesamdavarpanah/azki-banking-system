package com.backend.azkivam.accounts.utils;

public interface TransactionStrategy {
    void execute(String accountNumber, double amount);
}
