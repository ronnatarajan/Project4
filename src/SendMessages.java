package src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class SendMessages {
    public static void sellerSendsMessage(String customerEmail, String sellerEmail, String newMessage) {
        String fileName = "Accounts/" + customerEmail + ".txt";


        appendMessage(fileName, newMessage, sellerEmail, customerEmail);




        // This is to append the message to the seller's message history file as well
        String sellerFileName = "Accounts/" + sellerEmail + ".txt";

        appendMessage(sellerFileName, newMessage, sellerEmail, customerEmail);
    }

    public static void customerSendsMessage(String sellerEmail, String customerEmail, String newMessage) {
        String fileName = "Accounts/" + sellerEmail + ".txt";


        int index = customerEmail.indexOf('@');
        String customerName = customerEmail.substring(0, index);

        File messageFile = new File(fileName);

        appendMessage(fileName, newMessage, customerEmail, sellerEmail);


        // This is to append the message to the customer's message history file as well
        String customerFileName = "Accounts/" + customerEmail + ".txt";

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
