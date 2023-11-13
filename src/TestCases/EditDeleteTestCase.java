package src.TestCases;

import src.EditDelete;
import src.SendMessages;

/**
 * Project 4 -- Messaging System
 *
 *  Test Case for EditDelete
 *
 * @author Kenjie DeCastro, lab sec 23
 *
 * @version November 13, 2023
 */


public class EditDeleteTestCase {
    public static void main(String[] args) {
        String sellerEmail = "Apple@gmail.com";
        String customerEmail = "Harley123@gmail.com";
        String newMessage = "Hi, I am interested in purchasing one of your products";
        SendMessages.customerSendsMessage(sellerEmail, customerEmail, newMessage);


        System.out.println("deleteMessage Test Case");

        EditDelete.deleteMessage("Harley123@gmail.com", false);
        System.out.println("\nIf the message was deleted from the file, the test case passed.");
        System.out.println("When finished remember to delete the test files:" +
                "\n" + customerEmail + ".txt" +
                "\n" + sellerEmail + ".txt");

        System.out.println("\neditMessage Test Case");
        newMessage = "Hello Customer";
        SendMessages.sellerSendsMessage(customerEmail, sellerEmail, newMessage);
        EditDelete.editMessage("Apple@gmail.com", true);
        System.out.println("\nIf the message was edited in the correct file, the test case passed.");
        System.out.println("When finished remember to delete the test files:" +
                "\n" + customerEmail + ".txt" +
                "\n" + sellerEmail + ".txt");

    }
}
