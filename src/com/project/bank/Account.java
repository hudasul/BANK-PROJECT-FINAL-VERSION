package com.project.bank;

public abstract class Account {
    private String accountId;
    private String customerId;
    private String accountType;
    private double balance;
    private boolean isActive;
    private int overdraftCount;
    private DebitCard debitCard;
}
