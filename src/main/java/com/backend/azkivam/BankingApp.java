package com.backend.azkivam;

import com.backend.azkivam.accounts.configs.AppConfig;
import com.backend.azkivam.accounts.models.BankAccount;
import com.backend.azkivam.accounts.services.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankingApp {
    private final AccountService accountService;
    private final Scanner scanner;
    private final ExecutorService executorService;

    public BankingApp(AccountService accountService) {
        this.accountService = accountService;
        this.scanner = new Scanner(System.in);
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to the Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    executorService.submit(this::createAccount);
                    break;
                case 2:
                    executorService.submit(this::depositMoney);
                    break;
                case 3:
                    executorService.submit(this::withdrawMoney);
                    break;
                case 4:
                    executorService.submit(this::transferMoney);
                    break;
                case 5:
                    executorService.submit(this::checkBalance);
                    break;
                case 6:
                    shutdownExecutor();
                    System.out.println("Exiting the Banking System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();

        BankAccount bankAccount = new BankAccount(accountNumber, accountHolderName);
        accountService.createAccount(bankAccount);
        System.out.println("Account created successfully!");
    }

    private void depositMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            accountService.deposit(accountNumber, amount);
            System.out.println("Deposit successful!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdrawMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            accountService.withdraw(accountNumber, amount);
            System.out.println("Withdrawal successful!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void transferMoney() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter target account number: ");
        String targetAccountNumber = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            accountService.transferFund(accountNumber, targetAccountNumber, amount);
            System.out.println("Transfer successful!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        try {
            BankAccount account = accountService.getAccount(accountNumber)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            System.out.println("Your balance is: " + account.getBalance());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        BankingApp bankingApp = new BankingApp(accountService);
        bankingApp.start();
    }
}
