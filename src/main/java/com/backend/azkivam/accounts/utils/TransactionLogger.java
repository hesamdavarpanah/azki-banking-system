package com.backend.azkivam.accounts.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class TransactionLogger implements TransactionObserver {

    private static final Logger logger = LoggerFactory.getLogger(TransactionLogger.class);
    private final String logFilePath;

    public TransactionLogger() {
        this.logFilePath = "transactions.log";
    }

    public TransactionLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void onTransaction(String accountNumber, String transactionType, double amount) {
        String logEntry = String.format("%s: %s - Account: %s, Amount: %.2f%n",
                LocalDateTime.now(), transactionType, accountNumber, amount);
        logToFile(logEntry);
    }

    private void logToFile(String logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            logger.error("Failed to log transaction: {}", e.getMessage(), e);
        }
    }
}
