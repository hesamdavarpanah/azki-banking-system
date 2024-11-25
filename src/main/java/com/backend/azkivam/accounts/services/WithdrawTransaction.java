package com.backend.azkivam.accounts.services;

import com.backend.azkivam.accounts.utils.TransactionLogger;
import com.backend.azkivam.accounts.utils.TransactionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawTransaction implements TransactionStrategy {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionLogger transactionLogger;

    @Override
    public void execute(String accountNumber, double amount) {
        accountService.withdraw(accountNumber, amount);
        transactionLogger.onTransaction(accountNumber, "Withdraw", amount);
    }
}
