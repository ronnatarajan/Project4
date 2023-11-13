package src;

import java.io.*;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class Accounts {
    public static String checkAccount(String email, String password, String path) {

        try {
            File f = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));

            boolean found = false;
            String line = bufferedReader.readLine();
            while (line != null) {
                // Turns the read line into a String Array to help parse the email and password
                // of the account
                String[] lineArr = line.split(",");

                if ((email.equals(lineArr[0])) && (password.equals(lineArr[1]))) {
                    return "Found account";
                } else if ((email.equals(lineArr[0]))){
                    return "Found account";
                }
                line = bufferedReader.readLine();
            }


        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't read this line!");
            e.printStackTrace();
        }

        return "";
    }


    // adds a customer account to the Customer Accounts list and creates an individual file
    // for the customer where its messages are stored
    public static String addCustomerAccount(String customerEmail, String customerPassword) {
        // For a customer trying to sign up, checks to see if the account has already been created before
        System.out.println(checkAccount(customerEmail, customerPassword, "Database/Lists/CustomerAccountsList.txt"));
        if (checkAccount(customerEmail, customerPassword, "Database/Lists/SellerAccountsList.txt").equals("Found account")
                || checkAccount(customerEmail, customerPassword, "Database/Lists/CustomerAccountsList.txt").equals("Found account")) {
            System.out.println("Customer account already exists!");
            return "Customer account already exists";
        }

        // Writes the account in the specified format to the Customer Accounts list file
        // Creates accounts own individual text file
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/CustomerAccountsList.txt", true)));
            printWriter.println(customerEmail + "," + customerPassword + "," + "[]" + "," + "[]");

            File f = new File("Accounts/" + customerEmail + ".txt");
            f.createNewFile();

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "Customer account can be created";
    }

    // adds a seller account to Seller Accounts list and creates an individual file
    // for the seller where its messages are stored
    public static String addSellerAccount(String sellerEmail, String sellerPassword) {
        // For a seller trying to sign up, checks to see if the account has already been created before
        if (checkAccount(sellerEmail, sellerPassword, "Database/Lists/SellerAccountsList.txt").equals("Found account")
                || checkAccount(sellerEmail, sellerPassword, "Database/Lists/CustomerAccountsList.txt").equals("Found account")) {
            System.out.println("Seller account already exists!");
            return "Seller account already exists!";
        }

        // Writes the account in the specified format to the Seller Accounts list file
        // Creates accounts own individual text file
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/SellerAccountsList.txt", true)));
            printWriter.println(sellerEmail + "," + sellerPassword + "," + "[]" + "," + "[]");

            File f = new File("Accounts/" + sellerEmail + ".txt");
            f.createNewFile();

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("SellerAccountsList.txt does not exist!");
        }
        return "Seller account can be created";
    }
}


