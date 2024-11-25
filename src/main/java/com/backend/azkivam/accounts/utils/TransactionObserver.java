package com.backend.azkivam.accounts.utils;

public interface TransactionObserver {
    void onTransaction(String accountNumber, String transactionType, double amount);
}
