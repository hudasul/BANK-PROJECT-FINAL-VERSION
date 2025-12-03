package com.project.bank;
import java.io.*;
import java.util.UUID;


public class AuthService {

    PasswordHasher hasher = new PasswordHasher();
    public Customer register(String firstName, String lastName, String email, String password){
        String hashed = hasher.encypt(password);
        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(firstName, lastName, id, hashed, email);

        try(PrintWriter pw = new PrintWriter(new FileWriter("Customer-"+email+".txt"))){
            pw.println(firstName);
            pw.println(lastName);
            pw.println(email);
            pw.println(id);
            pw.println(hashed);
        } catch(IOException e){
            return null;
        }
        return customer;
    }
    public Customer login(String email, String password){

        File f = new File("Customer-" + email + ".txt");

        if(!f.exists()) return null;

        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String fName = br.readLine();
            String lName = br.readLine();
            String userEmail = br.readLine();
            String userId = br.readLine();
            String hashed = br.readLine();

            if(hasher.check(password, hashed)){
                return new Customer(fName, lName, userId, hashed, userEmail);
            }

        } catch(IOException e){

        }

        return null;
    }


}
