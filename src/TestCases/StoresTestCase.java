package src.TestCases;

import src.Stores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class StoresTestCase {
    public static void main(String[] args) {

        // Test Data
        String stores = "Bob's Hardware,Bob's Furniture";
        String sellerEmail = "Bob@gmail.com";
        appendStoresTestCase(stores, sellerEmail);


        // Error Test Data
        stores = "";
        System.out.println("\n\nERROR TEST DATA:");
        appendStoresTestCase(stores, sellerEmail);
    }

    // appendStores Test Case
    public static void appendStoresTestCase(String stores, String sellerEmail) {
        System.out.println("appendStores Test Case");



        Stores.appendStores(stores, sellerEmail);

        boolean stringFoundStore = false;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Lists/StoresList.txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(sellerEmail + ":" + stores)) {
                    stringFoundStore = true;
                } else {
                    stringFoundStore = false;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (stringFoundStore) {
            System.out.println("Test Passed: The store was written to the StoresList.txt file. Did not crash.");
        } else {
            System.out.println("Test Failed: The store was not written to the StoresList.txt file");
        }

        System.out.println("\nRemember to delete the following lines in the StoresList.txt file and leave an extra empty line!" +
                "\n" + sellerEmail + ":" + stores);
    }
}
