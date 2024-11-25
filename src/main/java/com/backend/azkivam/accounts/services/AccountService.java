package com.backend.azkivam.accounts.services;

import com.backend.azkivam.accounts.models.BankAccount;
import com.backend.azkivam.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepositTransaction depositTransaction;

    @Autowired
    private WithdrawTransaction withdrawTransaction;

    @Autowired
    private TransferTransaction transferTransaction;

    public Optional<BankAccount> getAccount(String accountNumber) {
        return accountRepository.findBankAccountByAccountNumber(accountNumber);
    }

    public void createAccount(BankAccount bankAccount) {
        accountRepository.save(bankAccount);
    }

    @Transactional
    public synchronized void deposit(String accountNumber, double amount) {
        BankAccount bankAccount = accountRepository.findBankAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));

        depositTransaction.execute(accountNumber, amount);
    }

    @Transactional
    public synchronized void withdraw(String accountNumber, double amount) {
        BankAccount bankAccount = accountRepository.findBankAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));

        withdrawTransaction.execute(accountNumber, amount);
    }

    @Transactional
    public synchronized void transferFund(String accountNumber, String targetAccountNumber, double amount) {
        BankAccount sourceAccount = accountRepository.findBankAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Source bank account not found"));

        BankAccount targetAccount = accountRepository.findBankAccountByAccountNumber(targetAccountNumber)
                .orElseThrow(() -> new RuntimeException("Target bank account not found"));

        transferTransaction.setTargetAccountNumber(targetAccountNumber);
        transferTransaction.execute(accountNumber, amount);
    }
}
