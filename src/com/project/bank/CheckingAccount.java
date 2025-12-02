package com.project.bank;

public class CheckingAccount extends Account{
    public CheckingAccount(String accountId, String customerId, DebitCard debitCard) {
        super(accountId, customerId, "Checking", true, 0, debitCard);
    }
}
