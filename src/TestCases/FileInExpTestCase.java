package src.TestCases;

import src.FileInExp;
import src.SendMessages;

/**
 * Project 4 -- Messaging System
 *
 *  Test Case for FileInExp
 *
 * @author Kenjie DeCastro, lab sec 23
 *
 * @version November 13, 2023
 */

public class FileInExpTestCase {
    public static void main(String[] args) {
        // Test Data
        String sellerEmail = "Apple@gmail.com";
        String customerEmail = "Harley123@gmail.com";
        String newMessage = "Hi, I am interested in purchasing one of your products";
        SendMessages.customerSendsMessage(sellerEmail, customerEmail, newMessage);

        // exportMessage Test Case
        System.out.println("exportMessage Test Case");
        String user = "Harley123@gmail.com";
        FileInExp.exportMessage(user);
        System.out.println("If the user's messages was successfully exported to the desired location, then the exportMessage " +
                "Test Case passed.\n");

        System.out.println("importMessage Test Case");
        System.out.println("Pass in the file destination, initial file location, and whether or not " +
                "the user is a seller in the code below. If the user's message was succesfully imported to the desired location," +
                "then the importMessage Test Case passed.\n");

//        String to = "";
//        String from = "";
        boolean isSeller = false;
//
//        FileInExp.importMessage(to, from, isSeller);
    }
}
