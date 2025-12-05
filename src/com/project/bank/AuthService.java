package com.project.bank;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;


public class AuthService {

    PasswordHasher hasher = new PasswordHasher();
    public Customer register(String firstName, String lastName, String email, String password, ArrayList<Account>accounts){
        String hashed = hasher.encypt(password);
        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(firstName, lastName, id, hashed, email);

        try(PrintWriter pw = new PrintWriter(new FileWriter("Customer-"+email+".txt"))){
            pw.println(firstName);
            pw.println(lastName);
            pw.println(email);
            pw.println(id);
            pw.println(hashed);
//            pw.println(accounts.stream().map(e->e));
            accounts.forEach(e-> pw.println(e));
        } catch(IOException e){
            return null;
        }
        return customer;
    }
    public Customer login(String email, String password) {
        File f = new File("Customer-" + email + ".txt");
        if (!f.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String fName = br.readLine();
            String lName = br.readLine();
            String userEmail = br.readLine();
            String userId = br.readLine();
            String hashed = br.readLine();

            if (!hasher.check(password, hashed)) return null;

            Customer customer = new Customer(fName, lName, userId, hashed, userEmail);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ACCOUNT|")) {
                    String[] parts = line.split("\\|");
                    String accountId = parts[1];
                    String accountCustomerId = parts[2];
                    String accountType = parts[3];
                    double balance = Double.parseDouble(parts[4]);
                    boolean isActive = Boolean.parseBoolean(parts[5]);
                    int overdraftCount = Integer.parseInt(parts[6]);

                    DebitCard card = null;
                    if (parts.length > 7 && parts[7].equals("CARD")) {
                        String cardId = parts[8];
                        String cardType = parts[9];
                        double dailyWithdraw = Double.parseDouble(parts[10]);
                        double dailyDeposit = Double.parseDouble(parts[11]);
                        double dailyTransfer = Double.parseDouble(parts[12]);

                        switch (cardType) {
                            case "MasterCard":
                                card = new MasterCard(cardId, accountId,customer.getId());
                                break;
                            case "Titanium":
                                card = new MasterCardTitanium(cardId, accountId,customer.getId());
                                break;
                            case "Platinium":
                                card = new MasterCardPlatinium(cardId, accountId,customer.getId());
                                break;
                        }

                        if (card != null) {
                            card.setDailyWithdrawLimit(dailyWithdraw);
                            card.setDailyDepositLimit(dailyDeposit);
                            card.setDailyTransferLimit(dailyTransfer);
                        }
                    }

                    Account account = null;
                    if (accountType.equals("Saving")) {
                        account = new SavingAccount(accountId, accountCustomerId, card);
                    } else if (accountType.equals("Checking")) {
                        account = new CheckingAccount(accountId, accountCustomerId, card);
                    }

                    if (account != null) {
                        account.setBalance(balance);
                        for (int i = 0; i < overdraftCount; i++) {
                            account.incrementOverdraft();
                        }
                        customer.addAccount(account);
                    }
                }
            }

            return customer;

        } catch (IOException e) {

        }

        return null;
    }


}
