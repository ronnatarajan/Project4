package src;

import java.io.*;
import java.util.Scanner;

public class SendMessages {

    public static void messageInterface(String storeName) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please type your message to " + storeName + "below and hit enter to send.");
        System.out.println("\n");


    }


    public static void printCustomerMessages(String customerEmail, String sellerEmail) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Accounts/CustomerAccounts/" + customerEmail + "/" + sellerEmail + ".txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printSellerMessages(String sellerEmail, String customerEmail) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Accounts/CustomerAccounts/" + sellerEmail + "/" + customerEmail + ".txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void sellerSendsMessage(String customerEmail, String sellerEmail, String newMessage, String storeName) {
        String fileName = "Database/Accounts/CustomerAccounts/" + customerEmail + "/" +
                sellerEmail + ".txt";


        appendMessage(fileName, newMessage, storeName);




        // This is to append the message to the seller's message history file as well
        String sellerFileName = "Database/Accounts/SellerAccounts/" + sellerEmail + "/" +
                customerEmail + ".txt";

        appendMessage(sellerFileName, newMessage, storeName);
    }

    public static void customerSendsMessage(String sellerEmail, String customerEmail, String newMessage) {
        String fileName = "Database/Accounts/SellerAccounts/" + sellerEmail + "/" +
                customerEmail + ".txt";


        int index = customerEmail.indexOf('@');
        String customerName = customerEmail.substring(0, index);

        File messageFile = new File(fileName);

        appendMessage(fileName, newMessage, customerName);


        // This is to append the message to the customer's message history file as well
        String customerFileName = "Database/Accounts/CustomerAccounts/" + customerEmail + "/" +
                sellerEmail + ".txt";

        File customerMessageFile = new File(customerFileName);

        appendMessage(customerFileName, newMessage, customerName);

    }



    public static void appendMessage(String fileName, String newMessage, String sender) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName,true)));
            printWriter.print(sender + ": " + newMessage + "\n");

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
