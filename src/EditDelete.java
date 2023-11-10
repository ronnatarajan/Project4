import java.util.*;
import java.io.*;

public class EditDelete {

    public static void deleteMessage(String user) {
        try{
            Scanner input = new Scanner(System.in);
            String path = user;
            File f = new File(path);
            Scanner reader = new Scanner(f);
            int totLines= 0;
            ArrayList<String> lines = new ArrayList<String>();
            while (reader.hasNextLine()) {
                totLines++;
                lines.add(reader.nextLine());
            }
            String answer;
            do {
                System.out.println("Enter a message to add to delete (1 - " + totLines + ")");
                int messageNum = Integer.parseInt(input.nextLine());
                totLines--;
                lines.remove(messageNum - 1);
                System.out.println("Add more messages to export? (y/n)");
                answer = input.nextLine().toLowerCase();
            } while(answer.equals("y"));

            f.delete();
            File newFile = new File(path);
            newFile.createNewFile();

            FileWriter writer = new FileWriter(newFile);
            for (String i: lines) {
                writer.write(i);
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editMessage(String user) {
        try {
        Scanner input = new Scanner(System.in);
        String path = user;
        File f = new File(path);
        Scanner reader = new Scanner(f);
        int totLines= 0;
        ArrayList<String> lines = new ArrayList<String>();
        while (reader.hasNextLine()) {
            totLines++;
            lines.add(reader.nextLine());
        }
        String answer;
        do {
            System.out.println("Enter a message to add to edit (1 - " + totLines + ")");
            int messageNum = Integer.parseInt(input.nextLine());
            System.out.println("Enter new message:");
            String newMessage = input.nextLine() + " - edited";
            String[] oldMessage = lines.get(messageNum - 1).split(",");
            String recipient = oldMessage[1];

            String path2 = recipient;
            File recipientMessages = new File(path2);
            Scanner reader2 = new Scanner(recipientMessages);
            ArrayList<String> lines2 = new ArrayList<String>();

            oldMessage[2] = newMessage;
            newMessage = String.join(",", oldMessage);
            lines.set(messageNum - 1, newMessage);

            while(reader2.hasNextLine()) {
                lines2.add(reader2.nextLine());
                if (lines2.get(lines2.size()-1).equals(lines.get(messageNum - 1))) {
                    lines2.set(lines2.size() - 1, newMessage);
                }
            }
            
            f.delete();
            File f2 = new File(path);
            f2.createNewFile();
            FileWriter writer = new FileWriter(f2);
            for (String i: lines) {
                writer.write(i);
            }
            writer.close();

            recipientMessages.delete();

            File recipientMessages2 = new File(path2);
            FileWriter writer2 = new FileWriter(recipientMessages2);
            for (String i: lines2) {
                writer2.write(i);
            }
            writer2.close();

            System.out.println("Add more messages to edit? (y/n)");
            answer = input.nextLine().toLowerCase();
        } while(answer.equals("y"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    }   
}