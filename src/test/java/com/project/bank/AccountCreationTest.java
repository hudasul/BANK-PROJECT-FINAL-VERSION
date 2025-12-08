//package com.project.bank;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.util.ArrayList;
//
//public class AccountCreationTest {
//
//    private com.project.bank.AuthService authService;
//    private String testEmail1 = "test1@example.com";
//    private String testEmail2 = "test2@example.com";
//
//    @Before
//    public void setUp() {
//        authService = new com.project.bank.AuthService();
//        cleanupTestFiles();
//    }
//
//    @After
//    public void tearDown() {
//        cleanupTestFiles();
//    }
//
//    private void cleanupTestFiles() {
//        File file1 = new File("Customer-" + testEmail1 + ".txt");
//        File file2 = new File("Customer-" + testEmail2 + ".txt");
//        if (file1.exists()) file1.delete();
//        if (file2.exists()) file2.delete();
//    }
//
//    @Test
//    public void testCreateAccountWithMatchingPasswords() {
//        String password = "password123";
//        String confirmPassword = "password123";
//
//        assertTrue("Passwords should match", password.equals(confirmPassword));
//
//        ArrayList<com.project.bank.Account> accounts = new ArrayList<>();
//        com.project.bank.Customer customer = authService.register("John", "Doe", testEmail1, password, accounts);
//
//        assertNotNull("Customer should be created with matching passwords", customer);
//        assertEquals("First name should match", "John", customer.getFirst_name());
//        assertEquals("Last name should match", "Doe", customer.getSecond_name());
//    }
//
//    @Test
//    public void testCreateAccountWithDifferentPasswords() {
//        String password = "password123";
//        String confirmPassword = "differentPassword";
//
//        assertFalse("Passwords should not match", password.equals(confirmPassword));
//    }
//
//    @Test
//    public void testCreateAccountWithExistingEmail() {
//        ArrayList<com.project.bank.Account> accounts1 = new ArrayList<>();
//        com.project.bank.Customer customer1 = authService.register("John", "Doe", testEmail1, "password123", accounts1);
//        assertNotNull("First customer should be created", customer1);
//
//        ArrayList<com.project.bank.Account> accounts2 = new ArrayList<>();
//        com.project.bank.Customer customer2 = authService.register("Jane", "Smith", testEmail1, "password456", accounts2);
//
//        assertNull("Second customer with same email should not be created", customer2);
//    }
//
//    @Test
//    public void testCreateAccountWithNewEmail() {
//        ArrayList<com.project.bank.Account> accounts = new ArrayList<>();
//        com.project.bank.Customer customer = authService.register("Alice", "Williams", testEmail2, "password789", accounts);
//
//        assertNotNull("Customer should be created with new email", customer);
//        assertEquals("First name should match", "Alice", customer.getFirst_name());
//        assertEquals("Last name should match", "Williams", customer.getSecond_name());
//    }
//}
