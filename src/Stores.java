package src;

import java.io.*;
import java.util.ArrayList;

/**
 * Project 4 -- Messaging System
 *
 *  Adding Stores to the StoresList.txt
 *
 * @author Kenjie DeCastro, lab sec 23
 *
 * @version November 13, 2023
 */

public class Stores {
    /**
     * @param stores
     * @param sellerEmail
     *
     * Writes a seller's created store and their email to the StoresList.txt file
     */
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
}
