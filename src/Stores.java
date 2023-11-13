package src;

import java.io.*;
import java.util.ArrayList;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class Stores {
    // Unused Method
    public static void createStore(String sellerEmail, String storeName) {

        String fileName = "Database/Accounts/SellerAccounts/" + sellerEmail + "/Stores.txt";

        appendStores(storeName,sellerEmail);

    }

    // Writes a seller's created store and their email to the StoresList.txt file
    public static void appendStores(String stores, String sellerEmail) {
        // Writes  store and email to the StoresList.txt in the proper format
        try {
            PrintWriter printWriter =
                    new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/StoresList.txt",true)));

            printWriter.print(sellerEmail + ":" + stores);
            printWriter.println();

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Unused Method
    public static ArrayList<String> allStoresList() {
        ArrayList<String> stores = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Database/Lists/StoresList.txt"));

            String line = "";

            int counter = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (counter == 0) {
                    stores.add(line);
                    counter++;
                } else if (counter == 1) {
                    counter = 0;
                }
            }

            bufferedReader.close();

            return stores;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Unused Method
    public static ArrayList<String> sellerStoresList(String sellerEmail) {
        ArrayList<String> stores = new ArrayList<>();

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("Database/Accounts/SellerAccounts/" + sellerEmail + "/Stores.txt"));

            String line = "";

            int counter = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (counter == 0) {
                    stores.add(line);
                    counter++;
                } else if (counter == 1) {
                    counter = 0;
                }
            }

            bufferedReader.close();

            return stores;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
