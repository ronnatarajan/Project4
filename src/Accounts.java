package src;

import java.io.*;

public class Accounts {

    public static String checkAccount(String email, String password, String path) {

        try {
            File f = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));

            boolean found = false;
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineArr = line.split(",");

                if ((email.equals(lineArr[0])) && (password.equals(lineArr[1]))) {
                    return "Found account";
                    // return "Customer account found!";
                    // for testing
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

//        return "Customer account not found!";
//        for testing
    }



    public static String addCustomerAccount(String customerEmail, String customerPassword) {
        System.out.println(checkAccount(customerEmail, customerPassword, "Database/Lists/CustomerAccountsList.txt"));
        if (checkAccount(customerEmail, customerPassword, "Database/Lists/SellerAccountsList.txt").equals("Found account") || checkAccount(customerEmail, customerPassword, "Database/Lists/CustomerAccountsList.txt").equals("Found account")) {
            System.out.println("Customer account already exists!");
            return "Customer account already exists";
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/CustomerAccountsList.txt", true)));
            printWriter.println(customerEmail + "," + customerPassword + "," + "[]" + "," + "[]");

            File f = new File("Accounts/" + customerEmail + ".txt");
            f.createNewFile();

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            System.out.println("CustomerAccountsList.txt does not exist!");
        }

        return "Customer account can be created";
    }

    public static String addSellerAccount(String sellerEmail, String sellerPassword) {
        if (checkAccount(sellerEmail, sellerPassword, "Database/Lists/SellerAccountsList.txt").equals("Found account") || checkAccount(sellerEmail, sellerPassword, "Database/Lists/CustomerAccountsList.txt").equals("Found account")) {
            System.out.println("Seller account already exists!");
            return "Seller account already exists!";
        }

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
    public static void main(String[] args) {

        // Test checkAccount method
        System.out.println("Testing checkAccount method:");
        String email = "test@example.com";
        String password = "password123";
        String path = "Database/Lists/CustomerAccountsList.txt";
        String result = Accounts.checkAccount(email, password, path);
        System.out.println(result);
        System.out.println();

        // Test addCustomerAccount method
        System.out.println("Testing addCustomerAccount method:");
        String newCustomerEmail = "newcustomer@example.com";
        String newCustomerPassword = "newpassword456";
        result = Accounts.addCustomerAccount(newCustomerEmail, newCustomerPassword);
        System.out.println(result);
        System.out.println();

        // Test addSellerAccount method
        System.out.println("Testing addSellerAccount method:");
        String newSellerEmail = "newseller@example.com";
        String newSellerPassword = "sellerpassword789";
        result = Accounts.addSellerAccount(newSellerEmail, newSellerPassword);
        System.out.println(result);
        System.out.println();

        // Test checkAccount after adding new accounts
        System.out.println("Testing checkAccount after adding accounts:");
        result = Accounts.checkAccount(newCustomerEmail, newCustomerPassword, path);
        System.out.println(result);
        result = Accounts.checkAccount(newSellerEmail, newSellerPassword, path);
        System.out.println(result);
    }
}


