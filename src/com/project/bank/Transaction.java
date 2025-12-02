package com.project.bank;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String accountId;
    private LocalDateTime date;
    private String type;
    private Account receiver;
    private double amount;


    public Transaction(String transactionId, String type, double amount){
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
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
        return date;
    }

    public void setDateTime(LocalDateTime date) {
        this.date = date;
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
