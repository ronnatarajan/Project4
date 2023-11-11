package src;

import java.io.*;
import java.util.ArrayList;

public class Stores {
    public static void createStore(String sellerEmail, String storeName) {

        String fileName = "Database/Accounts/SellerAccounts/" + sellerEmail + "/Stores.txt";

        appendStores(storeName,sellerEmail);

    }

    public static void appendStores(String storeName, String sellerEmail) {

//        // Appends to other seller's Stores.txt
//        try {
//            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName,true)));
//
//            printWriter.println(storeName);
//            printWriter.println(sellerEmail);
//
//            printWriter.flush();
//            printWriter.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        // Appends to the StoresList.txt
        try {
            PrintWriter printWriter =
                    new PrintWriter(new BufferedWriter(new FileWriter("Database/Lists/StoresList.txt",true)));

            printWriter.println(storeName);
            printWriter.println(sellerEmail);

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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
