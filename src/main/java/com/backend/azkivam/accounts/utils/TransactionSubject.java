package com.backend.azkivam.accounts.utils;

import java.util.ArrayList;
import java.util.List;

public class TransactionSubject {
    private final List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TransactionObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String accountNumber, String transactionType, double amount) {
        for (TransactionObserver observer : observers) {
            observer.onTransaction(accountNumber, transactionType, amount);
        }
    }
}
