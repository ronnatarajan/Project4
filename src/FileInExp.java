/**import java.util.*;
import java.io.*;

public class FileInExp {
    public static void exportMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file path of the message to be exported: ");
        String path = input.nextLine();
        try {
            File f = new File(path);
            File export = new File(path + "export.csv");
            FileWriter writer = new FileWriter(export);
            Scanner reader = new Scanner(f);
            int lines = 0;
            while (reader.nextLine() != null) lines++;
            String answer;
            do {
                reader = new Scanner(f);
                System.out.println("Enter a message to add to export (1 - " + lines + ")");
                int messageNum = Integer.parseInt(input.nextLine());
                for (int i = 0; i < messageNum - 1; i++) {
                    reader.nextLine();
                }
                writer.write(reader.nextLine() + "\n");
                System.out.println("Add more messages to export? (y/n)");
                answer = input.nextLine().toLowerCase();
            } while(answer.equals("y"));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importMessage(String to, String from, boolean isSeller) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path for the message to be imported: ");
        String path = input.nextLine();
        try {
            File f = new File(path);
            Scanner reader = new Scanner(f);
            String message = "";
            while (reader.hasNextLine()) {
                message += reader.nextLine();
            }

            if (isSeller) {
                File storesList = new File("Database/Lists/StoresList.txt");
                Scanner parseStores = new Scanner(storesList);
                String storeName;
                while (parseStores.hasNextLine()) {
                    storeName = parseStores.nextLine();
                    String sellerName = parseStores.nextLine();
                    if (sellerName.equals(from)) {
                        break;
                    }
                }

                SendMessages.sellerSendsMessage(to, from, message, storeName);
            } else {
                SendMessages.customerSendsMessage(to, from, message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 **/