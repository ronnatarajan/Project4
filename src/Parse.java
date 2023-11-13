package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author Ron Natarajan, lab sec 23
 *
 * @version November 13, 2023
 */

public class Parse {

    /**
     * parse CustomerAccountsList and SellerAccountsList and get a list of Users
     * @return ArrayList<User>
     */
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
                    //if blocking lists and invisible lists are empty, we don't need to update them
                    if (lineArr[2].equals("[]") && lineArr[3].equals("[]")) {
                        User user = new User(lineArr[0], lineArr[1], false);
                        userList.add(user);
                    } else {
                        //if blocking isn't empty, we need to parse it and update the user's blocking attribute
                        if (!lineArr[2].equals("[]") && lineArr[3].equals("[]")) {
                            String[] blocked = lineArr[2].substring(1, lineArr.length - 2).split(",");
                            ArrayList<User> blockedList = new ArrayList<>();
                            for (String i : blocked) {
                                blockedList.add(new User(i, true));
                            }
                            User user = new User(lineArr[0], lineArr[1], false);
                            user.setBlocked(blockedList);
                            userList.add(user);

                        } else if (lineArr[2].equals("[]") && !lineArr[3].equals("[]")) {
                            //if invisible isn't empty we need to parse it and update the user's invisible attribute
                            String[] invisible = lineArr[3].substring(1, lineArr.length - 2).split(",");
                            ArrayList<User> invisibleList = new ArrayList<>();
                            for (String i : invisible) {
                                invisibleList.add(new User(i, true));
                            }
                            User user = new User(lineArr[0], lineArr[1], false);
                            user.setInvisible(invisibleList);
                            userList.add(user);
                        } else  {
                            //if invisible and blocking aren't empty we need to parse them and update the user's
                            // invisible attribute and blocking attribute
                            String[] blocked = lineArr[2].substring(1, lineArr.length - 2).split(",");
                            String[] invisible = lineArr[3].substring(1, lineArr.length - 2).split(",");


                            ArrayList<User> blockedList = new ArrayList<>();
                            ArrayList<User> invisibleList = new ArrayList<>();

                            for (String i : blocked) {
                                blockedList.add(new User(i, true));
                            }
                            for (String i : invisible) {
                                invisibleList.add(new User(i, true));
                            }
                            User user = new User(lineArr[0], lineArr[1], false);
                            user.setBlocked(blockedList);
                            user.setInvisible(invisibleList);
                            userList.add(user);
                        }
                    }
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
                try {
                    //if blocking lists and invisible lists are empty, we don't need to update them
                    if (lineArr[2].equals("[]") && lineArr[3].equals("[]")) {
                        User user = new User(lineArr[0], lineArr[1], true);
                        userList.add(user);
                    } else {
                        //if blocking isn't empty, we need to parse it and update the user's blocking attribute
                        if (!lineArr[2].equals("[]") && lineArr[3].equals("[]")) {
                            String[] blocked = lineArr[2].substring(1, lineArr.length - 1).split(",");
                            ArrayList<User> blockedList = new ArrayList<>();
                            for (String i : blocked) {
                                blockedList.add(new User(i, false));
                            }
                            User user = new User(lineArr[0], lineArr[1], true);
                            user.setBlocked(blockedList);
                            userList.add(user);

                        } else if (lineArr[2].equals("[]") && !lineArr[3].equals("[]")) {
                            //if invisible isn't empty we need to parse it and update the user's invisible attribute
                            String[] invisible = lineArr[3].substring(1, lineArr.length - 1).split(",");
                            ArrayList<User> invisibleList = new ArrayList<>();
                            for (String i : invisible) {
                                invisibleList.add(new User(i, false));
                            }
                            User user = new User(lineArr[0], lineArr[1], true);
                            user.setInvisible(invisibleList);
                            userList.add(user);
                        } else  {
                            //if invisible and blocking aren't empty we need to parse them and update the user's
                            // invisible attribute and blocking attribute
                            String[] blocked = lineArr[2].substring(1, lineArr.length - 1).split(",");
                            String[] invisible = lineArr[3].substring(1, lineArr.length - 1).split(",");

                            ArrayList<User> blockedList = new ArrayList<>();
                            ArrayList<User> invisibleList = new ArrayList<>();

                            for (String i : blocked) {
                                blockedList.add(new User(i, false));
                            }
                            for (String i : invisible) {
                                invisibleList.add(new User(i, false));
                            }
                            User user = new User(lineArr[0], lineArr[1], true);
                            user.setBlocked(blockedList);
                            user.setInvisible(invisibleList);
                            userList.add(user);
                        }
                    }
                } catch(Exception e) {

                }
                sellerLine = sellerReader.readLine();
            }

            //close readers
            customerReader.close();
            sellerReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    /**
     * get all the buisiness information from the StoresList.txt file
     * @return HashMap of buisinesses
     */
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

    /**
     * get a list of a specified user's messages
     * @param email String
     * @param senderIsSeller boolean
     * @param recipientIsSeller boolean
     * @return Arraylist of Messages
     */
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
