package src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class SendMessages {

//    public static void messageInterface(String storeName) {
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Please type your message to " + storeName + "below and hit enter to send.");
//        System.out.println("\n");
//
//
//    }


//    public static void printCustomerMessages(String customerEmail, String sellerEmail) {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Accounts/CustomerAccounts/" + customerEmail + "/" + sellerEmail + ".txt"));
//
//            String line = "";
//
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static void printSellerMessages(String sellerEmail, String customerEmail) {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Accounts/CustomerAccounts/" + sellerEmail + "/" + customerEmail + ".txt"));
//
//            String line = "";
//
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public static void sellerSendsMessage(String customerEmail, String sellerEmail, String newMessage) {
        String fileName = "Database/Accounts/CustomerAccounts/" + customerEmail + "/" + "Messages.txt";


        appendMessage(fileName, newMessage, sellerEmail, customerEmail);




        // This is to append the message to the seller's message history file as well
        String sellerFileName = "Database/Accounts/SellerAccounts/" + sellerEmail + "/" + "Messages.txt";

        appendMessage(sellerFileName, newMessage, sellerEmail, customerEmail);
    }

    public static void customerSendsMessage(String sellerEmail, String customerEmail, String newMessage) {
        String fileName = "Database/Accounts/SellerAccounts/" + sellerEmail + "/" + "Messages.txt";


        int index = customerEmail.indexOf('@');
        String customerName = customerEmail.substring(0, index);

        File messageFile = new File(fileName);

        appendMessage(fileName, newMessage, customerEmail, sellerEmail);


        // This is to append the message to the customer's message history file as well
        String customerFileName = "Database/Accounts/CustomerAccounts/" + customerEmail + "/" + "Messages.txt";

        File customerMessageFile = new File(customerFileName);

        appendMessage(customerFileName, newMessage, customerEmail, sellerEmail);

    }



    public static void appendMessage(String fileName, String newMessage, String senderEmail, String recipientEmail) {
        Date currentDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y h:mm a");


        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName,true)));
            printWriter.println(senderEmail + "," + newMessage + "," + recipientEmail + "," + dateForm.format(currentDate));

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
