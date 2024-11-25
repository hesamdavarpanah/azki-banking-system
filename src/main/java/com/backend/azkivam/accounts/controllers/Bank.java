package com.backend.azkivam.accounts.controllers;

import com.backend.azkivam.accounts.models.BankAccount;
import com.backend.azkivam.accounts.payloads.requests.AccountNumber;
import com.backend.azkivam.accounts.payloads.requests.CreateAccountBank;
import com.backend.azkivam.accounts.payloads.requests.Transaction;
import com.backend.azkivam.accounts.payloads.requests.TransferFund;
import com.backend.azkivam.accounts.payloads.responses.MessageResponse;
import com.backend.azkivam.accounts.services.AccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/bank/", produces = "application/json", consumes = "application/json")
public class Bank {

    @Autowired
    private AccountService accountService;


    @RateLimiter(name = "accounts")
    @PostMapping("get-account/")
    public ResponseEntity<?> getAccount(@Valid @RequestBody AccountNumber accountNumber) {
        try {
            Optional<BankAccount> bankAccount = accountService.getAccount(accountNumber.getAccountNumber());
            return ResponseEntity.ok().body(bankAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RateLimiter(name = "accounts")
    @PostMapping("create-account/")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountBank createAccountBank) {
        try {
            BankAccount bankAccount = new BankAccount(createAccountBank.getAccountNumber(), createAccountBank.getAccountHolderName());
            accountService.createAccount(bankAccount);

            return MessageResponse.generateResponse("created", "the bankAccount has been created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RateLimiter(name = "accounts")
    @PostMapping("withdraw/")
    public ResponseEntity<?> withdrawMoney(@Valid @RequestBody Transaction transaction) {
        try {
            accountService.withdraw(transaction.getAccountNumber(), transaction.getAmount());
            return MessageResponse.generateResponse("successful", "the withdraw was successful", HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RateLimiter(name = "accounts")
    @PostMapping("deposit/")
    public ResponseEntity<?> depositMoney(@Valid @RequestBody Transaction transaction) {
        try {
            accountService.withdraw(transaction.getAccountNumber(), transaction.getAmount());
            return MessageResponse.generateResponse("successful", "the withdraw was successful", HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RateLimiter(name = "accounts")
    @PostMapping("transfer-fund/")
    public ResponseEntity<?> transferFund(@Valid @RequestBody TransferFund transferFund) {
        try {
            accountService.transferFund(transferFund.getAccountNumber(), transferFund.getTargetAccountNumber(), transferFund.getAmount());
            return MessageResponse.generateResponse("successful", "the transfer fund was successful", HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RateLimiter(name = "accounts")
    @PostMapping("display-balance/")
    public ResponseEntity<?> displayBalance(@Valid AccountNumber accountNumber) {
        try {
            Optional<BankAccount> account = accountService.getAccount(accountNumber.getAccountNumber());
            if (account.isPresent()) {
                String message = String.format("your balance is %s", account.get().getBalance());
                return MessageResponse.generateResponse("check your balance!", message, HttpStatus.OK);
            } else {
                return MessageResponse.generateResponse("not found", "could not find the account to show balance", HttpStatus.FAILED_DEPENDENCY);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
