package src.TestCases;

import src.InvalidMessageException;
import src.Message;
import src.User;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class MessageTestCase {

    public static void main(String[] args) throws InvalidMessageException {
        // Test Data
        User buyer = new User("Harley123@gmail.com", "Awesome", false);
        User seller = new User("Apple@icloud.com", "Apple", true);
        User seller2 = new User("Google@gmail.com", "Google", true);
        User buyer2 = new User("Dan@gmail.com", "Password", false);



        String newMessage = "Hello World";

        // Message Blocked by recipient Test Case
        seller.block(buyer);
        System.out.println("Messages Blocked by recipient Test Case");
        System.out.println("If exception is thrown, test case passed");
        Message message = new Message(newMessage, buyer, seller);

        // Seller Cannot Message Another Seller Test Case
        System.out.println("Seller Cannot Message Another Seller Test Case");
        System.out.println("If exception is thrown, test case passed");
        Message messageTwo = new Message(newMessage, seller, seller2);

        // Buyer Cannot Message Another Buyer Test Case
        System.out.println("Buyer Cannot Message Another Buyer Test Case");
        System.out.println("If exception is thrown, test case passed");
        Message messageThree = new Message(newMessage, buyer, buyer2);




    }

}
