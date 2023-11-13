package src;

import src.SendMessages;

import java.util.*;
import java.io.*;

/**
 * Project 4 -- Messaging System
 *
 *  Class to implement methods for importing text to conversations and exporting conversations
 *
 * @author Ryan Kenney, lab sec 23
 *
 * @version November 13, 2023
 */

public class FileInExp {
    // 
    /**
     * @param user
     * Method to export messages
     */
    public static void exportMessage(String user) {
        Scanner input = new Scanner(System.in);
        // Opens the users message data
        String path = "Accounts/" + user + ".txt";
        try {
            File f = new File(path);
            // Gets the user to input the path for the csv to be exported to
            System.out.println("Enter Path For Export to be Saved to:");
            File export = new File(input.nextLine() + "-export.csv");
            // Makes the csv export file
            export.createNewFile();
            FileWriter writer = new FileWriter(export);
            Scanner reader = new Scanner(f);
            int totLines= 0;
            ArrayList<String> lines = new ArrayList<String>();
            // Reads the messages of the user to figure out how many there are
            // and to cache them to write to the csv later
            while (reader.hasNextLine()) {
                totLines++;
                String l = reader.nextLine();
                lines.add(l);
                System.out.println(totLines + ": " + l);
            }
            String answer;
            do {
                if (totLines > 0) {
                // Main UI loop
                System.out.println("Enter a message to add to export (1 - " + totLines + ")");
                int messageNum = Integer.parseInt(input.nextLine());
                // Writes the inputed line to the CSV file and continues until user is done
                writer.write(lines.get(messageNum - 1) + "\n");
                System.out.println("Add more messages to export? (y/n)");
                answer = input.nextLine().toLowerCase();
                } else {
                    System.out.println("No Messages to Export!");
                    break;
                }
            } while(answer.equals("y"));
            writer.close();
        } catch (Exception e) {
            System.out.println("Please Enter Correct Information");
        }
    }
    // 
    /**
     * @param to
     * @param from
     * @param isSeller
     * Method to import messages
     */
    public static void importMessage(String to, String from, boolean isSeller) {
        Scanner input = new Scanner(System.in);
        // Prompts user for the input path
        System.out.println("Enter the path for the message to be imported: ");
        String path = input.nextLine();
        try {
            File f = new File(path);
            Scanner reader = new Scanner(f);
            String message = "";
            // Reads the message in from the file
            while (reader.hasNextLine()) {
                message += reader.nextLine();
            }
            // Uses the SendMessage method for the designated account type to send message
            if (isSeller) {
                SendMessages.sellerSendsMessage(to, from, message);
            } else {
                SendMessages.customerSendsMessage(to, from, message);
            }

        } catch (Exception e) {
            System.out.println("Please Enter Correct Information");
        }
    }
}
