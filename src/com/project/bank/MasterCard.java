package com.project.bank;

public class MasterCard extends DebitCard{
    public MasterCard(String cardId, String accountId,String userId) {
        super(cardId, accountId, "MasterCard",userId,500, 1000, 2000, 1000, 5000);
    }

    @Override
    public void showCardDetails() {
        System.out.println("MasterCard Card ID: " + getCardId() + ", Account ID: " + getAccountId());
    }
}
