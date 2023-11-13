package src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class SendMessages {

    // Sends a message to a customer from a seller by appending the message to bother their respective text file
    public static void sellerSendsMessage(String customerEmail, String sellerEmail, String newMessage) {
        String fileName = "Accounts/" + customerEmail + ".txt";


        // Call to append the message to customer's file
        appendMessage(fileName, newMessage, sellerEmail, customerEmail);




        // This is to append the message to the seller's message history file as well
        String sellerFileName = "Accounts/" + sellerEmail + ".txt";

        // Call to append the message to the customer's message history file as well
        appendMessage(sellerFileName, newMessage, sellerEmail, customerEmail);
    }

    // Sends a message to a seller from a customer by appending the message to both their respective text file
    public static void customerSendsMessage(String sellerEmail, String customerEmail, String newMessage) {
        String fileName = "Accounts/" + sellerEmail + ".txt";

        // Unused
        int index = customerEmail.indexOf('@');
        String customerName = customerEmail.substring(0, index);

        File messageFile = new File(fileName);
        // Unused

        // Call to append the message to the seller's file
        appendMessage(fileName, newMessage, customerEmail, sellerEmail);


        // This is to append the message to the customer's message history file as well
        String customerFileName = "Accounts/" + customerEmail + ".txt";

        // Unused
        File customerMessageFile = new File(customerFileName);
        // Unused

        // Call to append the message to the customer's file
        appendMessage(customerFileName, newMessage, customerEmail, sellerEmail);

    }


    // handles appending the messages to sender and receiver's text files in the specified format
    public static void appendMessage(String fileName, String newMessage, String senderEmail, String recipientEmail) {
        // creates a timestamp for the message
        Date currentDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y h:mm a");


        // writes the message to file in the specified format
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
