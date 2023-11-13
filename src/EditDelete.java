package src;

import java.util.*;
import java.io.*;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class EditDelete {
    // Method for deleting messages
    public static void deleteMessage(String user, boolean isSeller) {
        try{
            Scanner input = new Scanner(System.in);
            // Opens the file of the users messages
            String path = "Accounts/" + user + ".txt";
            File f = new File(path);
            Scanner reader = new Scanner(f);
            int totLines= 0;
            // Reads in total lines and saves lines for deleting later
            ArrayList<String> lines = new ArrayList<String>();
            while (reader.hasNextLine()) {
                totLines++;
                String l = reader.nextLine();
                lines.add(l);
                System.out.println(totLines + ": " + l);
            }
            String answer = "n";
            // Main UI loop
            do {
                // If there's lines to delete enter statement
                if (totLines > 0) {
                    System.out.println("Enter a message to add to delete (1 - " + totLines + ")");
                    int messageNum = Integer.parseInt(input.nextLine());
                    // Removes line from array list  of lines
                    totLines--;
                    lines.remove(messageNum - 1);
                    // Prompts user to continue or not
                    System.out.println("Add more messages to delete? (y/n)");
                    answer = input.nextLine().toLowerCase();
                } else {
                    System.out.println("No Messages To Delete!");
                    break;
                }
            } while(answer.equals("y"));

            // Deletes old user text file
            f.delete();
            File newFile = new File(path);
            newFile.createNewFile();
            // Replaces old message file with a new one with deleted messages
            FileWriter writer = new FileWriter(newFile);
            for (String i: lines) {
                writer.write(i);
                writer.write("\n");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Please Enter Correct Info");
        }
    }
    // Method to edit messages
    public static void editMessage(String user, boolean isSeller) {
        try {
        Scanner input = new Scanner(System.in);
        // Loads user messages file
        String path = "Accounts/" + user + ".txt";
        File f = new File(path);
        Scanner reader = new Scanner(f);
        int totLines= 0;
        // Reads in total lines and saves lines to edit later
        ArrayList<String> lines = new ArrayList<String>();
        while (reader.hasNextLine()) {
            totLines++;
            String l = reader.nextLine();
            lines.add(l);
            System.out.println(totLines + ": " + l);
        }
        String answer = "n";
        // Main UI loop
        do {
            if (totLines > 0) {
            // Prompts user for a message to edit
            System.out.println("Enter a message to add to edit (1 - " + totLines + ")");
            int messageNum = Integer.parseInt(input.nextLine());
            // Prompts user for the new message after edits
            System.out.println("Enter new message:");
            String newMessage = input.nextLine() + " - edited";
            // Reads in old unedited message from the array list of lines in the old file
            String[] oldMessage = lines.get(messageNum - 1).split(",");
            String oldString = String.join(",", oldMessage);
            String recipient = oldMessage[2];
            // Pulls the recipient from the old message and opens their message file
            String path2 = "Accounts/" + recipient + ".txt";
            File recipientMessages = new File(path2);
            Scanner reader2 = new Scanner(recipientMessages);
            ArrayList<String> lines2 = new ArrayList<String>();
            // Makes the new message with the edited text
            oldMessage[1] = newMessage;
            newMessage = String.join(",", oldMessage);
            lines.set(messageNum - 1, newMessage);
            // Reads the lines in from the recipients file
            while(reader2.hasNextLine()) {
                lines2.add(reader2.nextLine());
                if (lines2.get(lines2.size()-1).equals(oldString)) {
                    lines2.set(lines2.size() - 1, newMessage);
                }
            }
            // Deletes the old user's message file
            f.delete();
            // Replaces old file with a new one containing all old messages and edited message
            File f2 = new File(path);
            f2.createNewFile();
            FileWriter writer = new FileWriter(f2);
            for (String i: lines) {
                writer.write(i);
                writer.write("\n");

            }
            writer.close();
            // Deletes recipients old messages
            recipientMessages.delete();
            // Replaces the old recipient file wth new one with edited messages
            File recipientMessages2 = new File(path2);
            recipientMessages2.createNewFile();
            FileWriter writer2 = new FileWriter(recipientMessages2);
            for (String i: lines2) {
                writer2.write(i);
                writer2.write("\n");
            }
            writer2.close();
            // Prompts user if they want to continue or not
            System.out.println("Add more messages to edit? (y/n)");
            answer = input.nextLine().toLowerCase();
        } else {
            System.out.println("No messages to edit!");
            break;
        }
        } while(answer.equals("y"));
    } catch (Exception e) {
        System.out.println("Please Enter Correct Info");
    }
    }
}
