package com.project.bank;

public class SavingAccount extends Account{
    public SavingAccount(String accountId, String customerId, String accountType, boolean isActive, double balance, int overdraftCount, DebitCard debitCard) {
        super(accountId, customerId, "Saving", true, 0, debitCard);
    }
}
