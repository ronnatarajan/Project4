package src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMessagesTestCase {
    public static void main(String[] args) {
        // Test Data for customerSendsMessage
        String sellerEmail = "Apple@gmail.com";
        String customerEmail = "Harley123@gmail.com";
        String newMessage = "Hi, I am interested in purchasing one of your products";
        customerSendsMessageTestCase(sellerEmail, customerEmail, newMessage);

        // Test Data for sellerSendsMessage
        newMessage = "Hi customer! I am happy to answer your question!";
        sellerSendsMessageTestCase(customerEmail, sellerEmail, newMessage);
        // Note: If both methods passed, then it means the appendMessage method
        // passed as well.

        // Invalid Test Data for customerSendsMessage
        System.out.println("\nERROR TEST DATA:");
        newMessage = "";
        customerSendsMessageTestCase(sellerEmail, customerEmail, newMessage);

        System.out.println("\nERROR TEST DATA:");
        sellerSendsMessageTestCase(customerEmail, sellerEmail, newMessage);

    }

    public static void customerSendsMessageTestCase(String sellerEmail, String customerEmail, String newMessage) {
        System.out.println("\ncustomerSendsMessage Test Case");


        SendMessages.customerSendsMessage(sellerEmail, customerEmail, newMessage);

        // Checks if the message was stored in the seller's respective file
        boolean stringFoundSeller = false;

        try {
            Date currentDate = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y h:mm a");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("Accounts/" + sellerEmail + ".txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(customerEmail + "," + newMessage + "," + sellerEmail + "," + dateForm.format(currentDate))) {
                    stringFoundSeller = true;
                } else {
                    stringFoundSeller = false;
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (stringFoundSeller) {
            System.out.println("Test Passed: The message was written to the seller's file. Did not crash.");
        } else {
            System.out.println("Test Failed: The message was not written to the seller's file");
        }

        // Checks if the message was stored in the customer's respective file
        boolean stringFoundCustomer = false;

        try {
            Date currentDate = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y h:mm a");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("Accounts/" + customerEmail + ".txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(customerEmail + "," + newMessage + "," + sellerEmail + "," + dateForm.format(currentDate))) {
                    stringFoundCustomer = true;
                } else {
                    stringFoundCustomer = false;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (stringFoundCustomer) {
            System.out.println("Test Passed: The message was written to the customer's file. Did not crash.");
        } else {
            System.out.println("Test Failed: The message was not written to the customer's file");
        }

        System.out.println("\nRemember to delete the files you created for testing!");
        System.out.println("They were: \n"
                + sellerEmail + ".txt\n"
                + customerEmail +".txt");

    }


    public static void sellerSendsMessageTestCase(String customerEmail, String sellerEmail, String newMessage) {
        System.out.println("\nsellerSendsMessage Test Case");



        SendMessages.sellerSendsMessage(customerEmail, sellerEmail, newMessage);

        // Checks if the message was stored in the seller's respective file
        boolean stringFoundSeller = false;

        try {
            Date currentDate = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y h:mm a");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("Accounts/" + sellerEmail + ".txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(sellerEmail + "," + newMessage + "," + customerEmail + "," + dateForm.format(currentDate))) {
                    stringFoundSeller = true;
                } else {
                    stringFoundSeller = false;
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (stringFoundSeller) {
            System.out.println("Test Passed: The message was written to the seller's file. Did not crash.");
        } else {
            System.out.println("Test Failed: The message was not written to the seller's file");
        }

        // Checks if the message was stored in the customer's respective file
        boolean stringFoundCustomer = false;

        try {
            Date currentDate = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y h:mm a");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("Accounts/" + customerEmail + ".txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(sellerEmail + "," + newMessage + "," + customerEmail + "," + dateForm.format(currentDate))) {
                    stringFoundCustomer = true;
                } else {
                    stringFoundCustomer = false;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (stringFoundCustomer) {
            System.out.println("Test Passed: The message was written to the customer's file. Did not crash.");
        } else {
            System.out.println("Test Failed: The message was not written to the customer's file");
        }

        System.out.println("\nRemember to delete the files you created for testing!");
        System.out.println("They were: \n"
                + sellerEmail + ".txt\n"
                + customerEmail +".txt");

    }








}
