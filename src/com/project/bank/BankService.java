package com.project.bank;

public class BankService {
    private Account account;

    public BankService(Account account) {
        this.account = account;
    }

    public boolean withdraw(double amount){
        if(this.account.getDebitCard().getDailyDepositLimit()<amount){

            this.account.getDebitCard().withdraw(amount);

            this.account.setBalance(this.account.getBalance()-amount);
            return true;
        }
       return false;
    }
    public boolean deposit(double amount){
        if(this.account.getDebitCard().getDailyDepositLimit()<amount){

            this.account.getDebitCard().deposit(amount);

            this.account.setBalance(this.account.getBalance()-amount);
            return true;
        }
        return false;
    }
}
