package com.project.bank;

public class BankService {

    public boolean withdraw(Account account,double amount){
        if(!account.isActive()) return false;
        if(account.getDebitCard().withdraw(amount)){
            double newBalance = account.getBalance() - amount;
            if(newBalance < 0){
                account.incrementOverdraft();
                if(account.getOverdraftCount() >= 2){
                    newBalance -= 35;
                    account.setActive(false);
                }
            }
            account.setBalance(newBalance);
            return true;
        }
        return false;
    }
    public boolean selfdeposit(double amount , Account account){
        DebitCard card = account.getDebitCard();
        if(!account.isActive()) return false;
        if(card.deposit(amount, true)){
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }
    public boolean deposit(double amount , Account account){
        DebitCard card = account.getDebitCard();
        if(!account.isActive()) return false;
        if(card.deposit(amount, false)){
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    public boolean transfer(Account from, Account to, double amount){
        DebitCard card = from.getDebitCard();
        if(!from.isActive()) return false;
        boolean isOwnAccount = from.getCustomerId().equals(to.getCustomerId());
        if(card.transfer(amount, isOwnAccount)){
            double newFromBalance = from.getBalance() - amount;
            if(newFromBalance < 0){
                from.incrementOverdraft();
                if(from.getOverdraftCount() >= 2){
                    newFromBalance -= 35;
                    from.setActive(false);
                }
            }
            from.setBalance(newFromBalance);
            to.setBalance(to.getBalance() + amount);
            return true;
        }
        return false;
    }
}
