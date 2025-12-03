package com.project.bank;

import java.util.Scanner;

public class LoginMenu {

    private Scanner sc = new Scanner(System.in);
    private AuthService auth = new AuthService();

    public void run() {
        while (true) {
            System.out.println("----- BANK SYSTEM -----");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("have a good day.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    System.out.println();
            }
        }
    }

    private void register() {
        System.out.println("----- Register -----");
        System.out.print("first name: ");
        String first = sc.nextLine();

        System.out.print("last name: ");
        String last = sc.nextLine();

        System.out.print("email: ");
        String email = sc.nextLine();

        System.out.print("password: ");
        String pass = sc.nextLine();
        System.out.print("password: ");
        String confPass = sc.nextLine();

       if (confPass.equals(pass)){ Customer c = auth.register(first, last, email, pass);
        if (c == null) {
            System.out.println("Error creating account.");
        } else {
            System.out.println("Registered successfully! Please log in.");
        }}
       else {
           System.out.println("Password dont match");
       }
    }

    private void login() {
        System.out.println("----- Login -----");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        Customer customer = auth.login(email, pass);
        if (customer == null) {
            System.out.println("Invalid email or password.");
            return;
        }

        System.out.println("Login successful. Welcome " + customer.getFullName() + "!");


    }
}
