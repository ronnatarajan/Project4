package src.TestCases;

import src.Parse;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Project 4 -- Messaging System
 *
 *  Test Case for Parse
 *
 * @author Kenjie DeCastro, lab sec 23
 *
 * @version November 13, 2023
 */

public class ParseTestCase {
    public static void main(String[] args) {
        // getUsers Test Case
        System.out.println("getUsers Test Case");
        System.out.println(Parse.getUsers().toString());
        System.out.println("Check if the list printed above contains the users " +
                "within the current SellerAccountsList.txt and CustomerAccountsList.txt files");
        System.out.println("If so, the getUsers Test Case Passed");


        // businesses Test Case
        System.out.println("\nbusinesses Test Case");
        for (Map.Entry<String, String[]> entry : Parse.businesses().entrySet()) {
            System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue()));
        }
        System.out.println("Check if the list printed above contains the Stores " +
                "within the current StoresList.txt file");
        System.out.println("If so, the businesses Test Case passed");


        // getMessages Test Case
        System.out.println("\ngetMessages Test Case");
        System.out.println("With a message sent in the main menu, input the parameters in the code below to see if" +
                " the message is what is expected. If so, the getMessages Test Case passed.");

//        String email = "Harley123@gmail.com";
//        boolean senderIsSeller = false;
//        boolean recepientIsSeller = true;
//
//        System.out.println("\ngetMessages Test Class");
//        System.out.println(Parse.getMessages(email, senderIsSeller, recepientIsSeller));
    }
}
