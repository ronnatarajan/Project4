package src;

import java.io.*;

public class Accounts {

    public static String checkAccount(String email, String password, String path) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            String line = "";

            int counter = 0;
            String readEmail = "";
            String readPassword = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (counter == 0) {
                    readEmail = line;
                    counter++;
                } else if (counter == 1) {
                    readPassword = line;
                    counter = 0;
                }


                if ((email.equals(readEmail)) && (password.equals(readPassword))) {
                    return "Found account";
                    // return "Customer account found!";
                    // for testing
                } else if ((email.equals(readEmail)) || (password.equals(readPassword))) {
                    return "Email or password is incorrect";
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't read this line!");
            e.printStackTrace();
        }

        return "Could not find account";

//        return "Customer account not found!";
//        for testing
    }



    public static void addCustomerAccount(String customerEmail, String customerPassword) {
        if (checkAccount(customerEmail, customerPassword, "Database/Lists/CustomerAccountsList.txt").equals("Email or password is incorrect")) {
            System.out.println("Customer account already exists!");
            return;
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/CustomerAccountsList.txt", true)));
            printWriter.println(customerEmail + "," + customerPassword);

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            System.out.println("CustomerAccountsList.txt does not exist!");
        }

        new File("Database/Accounts/CustomerAccounts/" + customerEmail).mkdirs();
//        File messagesFile = new File("CustomerAccounts/" + customerEmail +
//                "/messages.txt")
    }

    public static void addSellerAccount(String sellerEmail, String sellerPassword) {
        if (checkAccount(sellerEmail, sellerPassword,"Database/Lists/SellerAccountsList.txt").equals("Email or password is incorrect")) {
            System.out.println("Seller account already exists!");
            return;
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/SellerAccountsList.txt", true)));
            printWriter.println(sellerEmail + "," + sellerPassword);

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("SellerAccountsList.txt does not exist!");
        }

        new File("Database/Accounts/SellerAccounts/" + sellerEmail).mkdirs();
        File storesFile = new File("Database/Accounts/SellerAccounts/" + sellerEmail +
                "/Stores.txt");
    }

}


