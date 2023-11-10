import java.util.*;
import java.io.*;

public class FileInExp {
    public static void exportMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file path of the message to be exported: ");
        String path = input.nextLine();
        try {
            File f = new File(path);
            File export = new File(path + "export.csv");
            export.createNewFile();
            FileWriter writer = new FileWriter(export);
            Scanner reader = new Scanner(f);
            int totLines= 0;
            ArrayList<String> lines = new ArrayList<String>();
            while (reader.hasNextLine()) {
                totLines++;
                lines.add(reader.nextLine());
            }
            String answer;
            do {
                System.out.println("Enter a message to add to export (1 - " + totLines + ")");
                int messageNum = Integer.parseInt(input.nextLine());
                writer.write(lines.get(messageNum - 1) + "\n");
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