package com.project.bank;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String accountId;
    private LocalDateTime dateTime;
    private String type;
    private double amount;

    public Transaction(String transactionId, String accountId, LocalDateTime dateTime, String type, double amount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.dateTime = dateTime;
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
