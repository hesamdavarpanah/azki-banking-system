package com.backend.azkivam.accounts.services;

import com.backend.azkivam.accounts.utils.TransactionLogger;
import com.backend.azkivam.accounts.utils.TransactionStrategy;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferTransaction implements TransactionStrategy {
    private final TransactionLogger transactionLogger;
    @Autowired
    private AccountService accountService;
    @Setter
    private String targetAccountNumber;

    @Autowired
    public TransferTransaction(TransactionLogger transactionLogger) {
        this.transactionLogger = transactionLogger;
    }

    @Override
    public void execute(String accountNumber, double amount) {
        accountService.transferFund(accountNumber, targetAccountNumber, amount);
        transactionLogger.onTransaction(accountNumber, "Transfer to " + targetAccountNumber, amount);
    }
}
