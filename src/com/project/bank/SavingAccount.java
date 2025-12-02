package com.project.bank;

public class SavingAccount extends Account{
    public SavingAccount(String accountId, String customerId, DebitCard debitCard) {
        super(accountId, customerId, "Saving", true, 0, debitCard);
    }
}
