package com.project.bank;

public abstract class BankServices {

    public void withdraw (Account account,double amount ){
        if(account.getDebitCard().Withdraw(amount)){
            account.getDebitCard().setDailyWithdrawLimit(account.getDebitCard().getDailyWithdrawLimit()+amount);
            account.setBalance(account.getBalance()-amount);
        }
    }
    public void deposit (Account account , double amount){
        if(account.getDebitCard().Withdraw(amount)){
            account.getDebitCard().setDailyDepositLimit(account.getDebitCard().getDailyOwnDepositLimit()+amount);
            account.setBalance(account.getBalance()+amount);
    }

    }

    public void transfer (Account account){





    }

}
