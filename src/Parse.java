package src;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Parse {
    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try{
            //read from customer file
            File customerFile = new File("Database/Lists/CustomerAccountsList.txt");
            BufferedReader customerReader = new BufferedReader(new FileReader(customerFile));
            String customerLine = customerReader.readLine();

            //loop through customer file and populate list with all users that are customers
            while (customerLine != null) {
                String[] lineArr = customerLine.split(",");
                try {
                    User user = new User(lineArr[0], lineArr[1], false);
                    userList.add(user);
                } catch(Exception e ) {
                    
                }

                customerLine = customerReader.readLine();
            }

            //read from seller file
            File sellerFile = new File("Database/Lists/SellerAccountsList.txt");
            BufferedReader sellerReader = new BufferedReader(new FileReader(sellerFile));
            String sellerLine = sellerReader.readLine();

            //loop through seller file and populate list with all users that are sellers
            while (sellerLine != null) {
                String[] lineArr = sellerLine.split(",");
                User user = new User(lineArr[0], lineArr[1], true);
                userList.add(user);
                sellerLine = sellerReader.readLine();
            }

            customerReader.close();
            sellerReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public static HashMap<String, String[]> businesses() {
        HashMap<String, String[]> map = new HashMap<>();

        try {
            //read from customer file
            File stores = new File("Database/Lists/StoresList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(stores));
            String line = reader.readLine();

            // [seller]:[b1,b2,b3,b4]}
            while (line != null) {
                String[] arr = line.split(":")[1].split(",");
                map.put(line.split(":")[0], arr);

                line = reader.readLine();
            }

            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Message> getMessages(String email, boolean senderIsSeller, boolean recipientIsSeller) {
        ArrayList<Message> messages = new ArrayList<>();
        File customerMessages = null;

        try{
            //read from customer file
            customerMessages = new File("Accounts/" + email + ".txt");
            BufferedReader customerReader = new BufferedReader(new FileReader(customerMessages));
            String customerLine = customerReader.readLine();

            //loop through customer file and populate list with all users that are customers
            while (customerLine != null) {
                String[] lineArr = customerLine.split(",");
                // senderEmail + "," + newMessage + "," + recipientEmail
                User sender = new User(lineArr[0], senderIsSeller);
                User receiver = new User(lineArr[2], recipientIsSeller);

                Message m = new Message(lineArr[1], sender, receiver);
                messages.add(m);

                customerLine = customerReader.readLine();
            }

        } catch (IOException | InvalidMessageException e) {
            throw new RuntimeException(e);
        }

        return messages;

    }
}
