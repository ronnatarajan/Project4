import java.io.*;

public class Accounts {

    public static String checkCustomerAccount(String customerEmail, String customerPassword) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Lists/CustomerAccountsList.txt"));

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


                if ((customerEmail.equals(readEmail)) && (customerPassword.equals(readPassword))) {
                    return "Found account";
                    // return "Customer account found!";
                    // for testing
                } else if ((customerEmail.equals(readEmail)) || (customerPassword.equals(readPassword))) {
                    return "Email or password is incorrect";
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("CustomerAccountsList.txt does not exist!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't read this line!");
            e.printStackTrace();
        }

        return "Could not find account";

//        return "Customer account not found!";
//        for testing
    }

    // could do this with a thread


    public static String checkSellerAccount(String sellerEmail, String sellerPassword) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Lists/SellerAccountsList.txt"));

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


                if ((sellerEmail.equals(readEmail)) && (sellerPassword.equals(readPassword))) {
                    return "Found account";

                    // return "Seller account found!";
                    // for testing
                } else if (sellerEmail.equals(readEmail) || (sellerEmail.equals(readPassword))) {
                    return "Email or password is incorrect";
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("SellerAccountsList.txt does not exist!");
        } catch (IOException e) {
            System.out.println("Can't read this line!");
        }

        return "Could not find account";
        // return "Seller account not found!";
        // for testing
    }



    public static void addCustomerAccount(String customerEmail, String customerPassword) {
        if (checkCustomerAccount(customerEmail, customerPassword).equals("Email or password is incorrect")) {
            System.out.println("Customer account already exists!");
            return;
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/CustomerAccountsList.txt", true)));

            printWriter.println(customerEmail);
            printWriter.println(customerPassword);

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
        if (checkCustomerAccount(sellerEmail, sellerPassword).equals("Email or password is incorrect")) {
            System.out.println("Seller account already exists!");
            return;
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/SellerAccountsList.txt", true)));

            printWriter.println(sellerEmail);
            printWriter.println(sellerPassword);

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


