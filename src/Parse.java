package src;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
                User user = new User(lineArr[0], lineArr[1], false);
                userList.add(user);

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

                sellerLine = customerReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }


    public static ArrayList<Message> getMessages(String email, boolean isSeller) {
        ArrayList<Message> messages = new ArrayList<>();
        File customerMessages = new File("Accounts/CustomerAcounts." + email);

        try{
            //read from customer file
            customerMessages = new File("Accounts/CustomerAcounts." + email);
            BufferedReader customerReader = new BufferedReader(new FileReader(customerMessages));
            String customerLine = customerReader.readLine();

            //loop through customer file and populate list with all users that are customers
            while (customerLine != null) {
                String[] lineArr = customerLine.split(",");
                // senderEmail + "," + newMessage + "," + recipientEmail
                User sender = new User(lineArr[0], isSeller);
                User receiver = new User(lineArr[2], isSeller);

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
