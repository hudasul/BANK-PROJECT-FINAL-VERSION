package com.project.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;


public class Customer extends User{

    private String status ;
    private ArrayList<Account> accounts;

    public Customer(String firstName, String lastName, String id, String password) {
        super(firstName, lastName, "Customer", id, password);
        this.status = "Active";
        this.accounts = new ArrayList<>();
    }


    @Override
    public String getRole() {
        return "Customer";
    }
    //also not sure if im going to keep this way like multiple acounts under a single user login or going to change it later idk
    public void addAccount(Account account){
        this.accounts.add(account);
    }
    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public void saveToFile(){
        try(PrintWriter pw = new PrintWriter(new FileWriter("Customer-"+getFirst_name()+"-"+getId()+".txt"))){
            pw.println(getId()+"|"+getFirst_name()+"|"+getSecond_name()+"|"+getPassword()+"|"+status);
            for(Account a : accounts){
                pw.println("ACCOUNT|"+a.getAccountId()+"|"+a.getAccountType()+"|"+a.getBalance()+"|"+a.getDebitCard().getCardType());
                for(Transaction t : a.getTransactions()){
                    pw.println("TRANSACTION|"+t.getTransactionId()+"|"+t.getType()+"|"+t.getAmount()+"|"+t.getDateTime());
                }
            }
        } catch(IOException e){ e.printStackTrace(); }
    }
    public static Customer loadFromFile(String firstName, String id){
        File f = new File("Customer-"+firstName+"-"+id+".txt");
        if(!f.exists()) return null;
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String line = br.readLine();
            String[] parts = line.split("\\|");
            Customer c = new Customer(parts[1],parts[2],parts[0],parts[3]);
            c.status = parts[4];

            Account currentAccount = null;
            while((line = br.readLine()) != null){
                parts = line.split("\\|");
                if(parts[0].equals("ACCOUNT")){
                    String accId = parts[1];
                    String accType = parts[2];
                    double balance = Double.parseDouble(parts[3]);
                    String cardType = parts[4];
                    DebitCard card;
                    switch(cardType){
                        case "MasterCard": card = new MasterCard("C-"+accId, accId); break;
                        case "Titanium": card = new MasterCardTitanium("C-"+accId, accId); break;
                        case "Platinium": card = new MasterCardPlatinium("C-"+accId, accId); break;
                        default: card = new MasterCard("C-"+accId, accId);
                    }
                    if(accType.equals("Checking")) currentAccount = new CheckingAccount(accId, c.getId(), card);
                    else currentAccount = new SavingAccount(accId, c.getId(), card);
                    currentAccount.setBalance(balance);
                    c.addAccount(currentAccount);
                } else if(parts[0].equals("TRANSACTION") && currentAccount!=null){
                    Transaction t = new Transaction(parts[1],parts[2],Double.parseDouble(parts[3]));
                    t.setDateTime(LocalDateTime.parse(parts[4]));
                    currentAccount.addTransaction(t);
                }
            }
            return c;
        } catch(Exception e){ e.printStackTrace(); return null; }
    }
    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
