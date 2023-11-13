package src;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

/**
 * Project 4 -- Messaging System
 *
 *  Integrates all the functionality of the classes and functions as a MainMenu
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class MainMenu {
    // prompts for user input
    public static String storesPrompt = "What stores would you like to register?\nPlease separate each store using a comma, do not put spaces in between (ex. Store1,Store2)";
    public static String welcomeMessage = "Welcome to Market Place Messaging System";
    public static String emailPrompt = "Please input your email.";
    public static String passwordPrompt = "Please input your password.";
    public static String passwordVerifyPrompt = "Please re-enter your password to confirm.";
    public static String farewellMessage = "Goodbye!";
    public static String signUpThankYou = "Thank you for signing up!";
    public static String signUpBuyerOrSeller = "Would you like to sign up as a Buyer 'B'  or a Seller 'S'?";
    public static String errorMessageBuyerOrSeller = "Error, please enter 'B' (Buyer) or 'S' (Seller)";
    public static String errorValidInput = "Error, please enter a valid input (1-3)";
    public static String errorPasswordMatch = "Error, the re-entered password doesn't not match the original";
    public static boolean isRunning = true;
    public static String errorInvalidCredentials = "Error, please enter valid credentials";
    public static boolean isWrong = false;
    public static boolean passwordMatchCheck = false;

    //user menus
    private static String ongoingMenu = "1. Login\n" +
            "2. Sign Up\n" +
            "3. Exit";

    private static String mainMenu = "1. View Messages\n" +
            "2. Create Message\n" +
            "3. Block User\n" +
            "4. Export Message\n" +
            "5. Import Message\n" +
            "6. Edit Message\n" +
            "7. Delete Message\n" +
            "8. Invisible\n" +
            "9. Exit";

    public static void main(String[] args) {
        //scanner for user input
        Scanner scanner = new Scanner(System.in);

        //print welcome message and get the list of current registered users
        System.out.println(welcomeMessage);
        ArrayList<User> users = Parse.getUsers();

        //keep running until user exits program
        while (isRunning) {
            try {
                //ask user to either log in, sign up, or exit the program
                System.out.println(ongoingMenu);
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                //if they select 1, they are logging in
                if (userChoice == 1) {
                    //keep reprompting until they enter a valid login
                    boolean correctInput = false;
                    String email = "";
                    String password = "";

                    while (!correctInput) {
                        //ask for email
                        System.out.println(emailPrompt);
                        email = scanner.nextLine();
                        //ask for password
                        System.out.println(passwordPrompt);
                        password = scanner.nextLine();

                        //see if account exits
                        String checkC = Accounts.checkAccount(email, password, "Database/Lists/CustomerAccountsList.txt");
                        String checkS = Accounts.checkAccount(email, password, "Database/Lists/SellerAccountsList.txt");

                        //if found, stop prompting for email and password, else print error message
                        if (checkC.equals("Found account") || checkS.equals("Found account")) {
                            correctInput = true;
                        } else {
                            System.out.println("{" + errorInvalidCredentials + "}");
                        }
                    }

                    //find the user object that is logged in
                    User loggedIn = null;
                    for (User user : users) {
                        if (user.getUsername().equals(email) && user.getPassword().equals(password)) {
                            loggedIn = user;
                        }
                    }

                    // see if user has new messages
                    try {
                        //see if number of lines in file has increased
                        Scanner checkMessages = new Scanner(new File("Database/Lists/UserSizes.txt"));
                        Scanner getLines = new Scanner(new File("Accounts/" + loggedIn.getUsername() + ".txt"));
                        int totLines = 0;
                        while (getLines.hasNextLine()) {
                            totLines++;
                            getLines.nextLine();
                        }
                        while (checkMessages.hasNextLine()) {
                            String[] l = checkMessages.nextLine().split(",");
                            if (l[0].equals(loggedIn.getUsername()) && Integer.parseInt(l[1]) != totLines) {
                                System.out.println("You have new messages!");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to check for new messages");
                    }
                    ArrayList<Message> userMessages = Parse.getMessages(email, loggedIn.isSeller(), !loggedIn.isSeller());


                    //keep printing main menu until user exits the program
                    boolean exited = false;
                    while (!exited) {
                        System.out.println(mainMenu);

                        try {
                            //ask for a selection number from one to nine
                            int selection = Integer.parseInt(scanner.next());
                            scanner.nextLine();

                            //ensure user input is between one and nine
                            if (selection < 1 || selection > 9) {
                                throw new NumberFormatException();
                            }
                            switch (selection) {
                                //view list of messages
                                case 1:
                                    //loop through arraylist and call toString
                                    for (Message message : userMessages) {
                                        System.out.println(message.toString());
                                    }
                                    break;
                                //send message
                                case 2:
                                    //ensure that there are two users registered
                                    if (users.size() < 2) {
                                        System.out.println("There's no one to message!");
                                    } else {
                                        // code for sellers sending messages
                                        if (loggedIn.isSeller()) {
                                            //seller implementation
                                            System.out.println("Pick one of the customers below to message");
                                            ArrayList<User> customers = new ArrayList<>();

                                            //build string to include customers that the user is allowed to see
                                            StringBuilder p = new StringBuilder();
                                            p.append("{");
                                            for (User user : users) {
                                                if (!user.isSeller() && loggedIn.canSee(user) && user != loggedIn) {
                                                    customers.add(user);
                                                    p.append(user.getUsername()).append(",");

                                                }
                                            }

                                            //get rid of last comma and print p
                                            p.deleteCharAt(p.length() - 1);
                                            p.append("}");
                                            System.out.println(p);

                                            //get customer selection
                                            String customerSelection = scanner.nextLine();

                                            //if customer selected is found, continue, else print error message
                                            boolean found = false;
                                            User recipient = null;
                                            for (User user : customers) {
                                                if (user.getUsername().equals(customerSelection)) {
                                                    found = true;
                                                    recipient = user;
                                                }
                                            }
                                            if (!found) {
                                                System.out.println("Please input one of the users allowed");
                                            } else {
                                                //ask for message text
                                                System.out.println("Please type in a message");
                                                String m = scanner.nextLine();

                                                //try to create message, if exception thrown, print error message
                                                try {

                                                    Message message = new Message(m, loggedIn, recipient);
                                                    userMessages.add(message);
                                                    System.out.println("Message added!");

                                                    //write message to file
                                                    SendMessages.sellerSendsMessage(
                                                            message.getRecipient().getUsername(),
                                                            message.getSender().getUsername(),
                                                            message.getMessage()
                                                    );

                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }

                                        } else {
                                            //customer implementation
                                            HashMap<String, String[]> map = Parse.businesses();
                                            //get 2D array of stores
                                            String[][] stores = map.values().toArray(new String[0][]);

                                            //allow user to unput a store or seller
                                            System.out.println("Do you wish to message a store or a seller directly? (Enter 'store' or 'seller')");
                                            String messageType = scanner.nextLine();

                                            //if user selected store
                                            if (messageType.equalsIgnoreCase("store")) {
                                                System.out.println("Select one of the following stores to message");
                                                StringBuilder p = new StringBuilder("{");

                                                //get associated sellers
                                                ArrayList<String> sellerEmails = new ArrayList<>();
                                                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                    sellerEmails.add(entry.getKey());
                                                }

                                                //check valid sellers and print out their stores
                                                ArrayList<String> validSellers = new ArrayList<>();
                                                for (User u : users) {
                                                    if (u.isSeller()) {
                                                        for (String e : sellerEmails) {
                                                            if (u.getUsername().equals(e) && loggedIn.canSee(u)) {
                                                                validSellers.add(e);
                                                            }
                                                        }
                                                    }
                                                }

                                                for (String seller : validSellers) {
                                                    String[] associatedStores = map.get(seller);
                                                    for (String store : associatedStores) {
                                                        p.append(store).append(",");
                                                    }
                                                }
                                                p.append("}");
                                                p.deleteCharAt(p.length() - 2);
                                                System.out.println(p);

                                                //get specific store that the user wants to message
                                                String store = scanner.nextLine();
                                                String[] arr = null;

                                                //get the specific storeList that the requested store is in
                                                for (String[] storeList : stores) {
                                                    for (String s : storeList) {
                                                        if (s.equals(store)) {
                                                            arr = storeList;
                                                            break;
                                                        }
                                                    }
                                                }

                                                //get key (seller) of specific storelist
                                                String associatedSeller = "";
                                                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                    if (entry.getValue().equals(arr)) {
                                                        associatedSeller = entry.getKey();
                                                    }
                                                }

                                                //get User object of seller email
                                                User recipient = null;
                                                boolean found = false;
                                                for (User u : users) {
                                                    if (u.getUsername().equals(associatedSeller)) {
                                                        recipient = u;
                                                        found = true;
                                                    }
                                                }

                                                //if seller found, continue. Else, display error message
                                                if (!found) {
                                                    System.out.println("Please enter a valid user to message");
                                                } else {
                                                    //ask for message text
                                                    System.out.println("Please enter a message");
                                                    String mess = scanner.nextLine();

                                                    //try to create message
                                                    try {
                                                        Message m = new Message(mess, loggedIn, recipient);
                                                        userMessages.add(m);
                                                        System.out.println("Message added!");

                                                        //write message to file
                                                        SendMessages.customerSendsMessage(
                                                                m.getRecipient().getUsername(),
                                                                m.getSender().getUsername(),
                                                                m.getMessage()
                                                        );
                                                    } catch (Exception e) {
                                                        //if message creation resulted in an exception,
                                                        // print the error message
                                                        System.out.println(e.getMessage());
                                                    }
                                                }

                                            } else if (messageType.equalsIgnoreCase("seller")) {
                                                //implementation for sellers
                                                System.out.println("Select one of the following sellers to message");
                                                StringBuilder p = new StringBuilder("{");

                                                //get list of seller emails
                                                ArrayList<String> sellerEmails = new ArrayList<>();
                                                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                    sellerEmails.add(entry.getKey());
                                                }

                                                //get list of valid seller emails to message
                                                ArrayList<String> validSellers = new ArrayList<>();
                                                for (User u : users) {
                                                    if (u.isSeller()) {
                                                        for (String e : sellerEmails) {
                                                            if (u.getUsername().equals(e) && loggedIn.canSee(u)) {
                                                                validSellers.add(e);
                                                            }
                                                        }
                                                    }
                                                }

                                                //print out valid sellers
                                                for (String seller : validSellers) {
                                                    p.append(seller).append(",");
                                                }
                                                //print p
                                                p.append("}");
                                                p.deleteCharAt(p.length() - 2);
                                                System.out.println(p);

                                                //see if seller actually exits
                                                String seller = scanner.nextLine();
                                                User recipient = null;
                                                boolean found = false;
                                                for (User u : users) {
                                                    if (u.getUsername().equals(seller)) {
                                                        recipient = u;
                                                        found = true;
                                                    }
                                                }

                                                //if seller not found, print error. Else continue
                                                if (!found) {
                                                    System.out.println("Please enter a valid user to message");
                                                } else {
                                                    //if seller found, ask for a message
                                                    System.out.println("Please enter a message");
                                                    String mess = scanner.nextLine();

                                                    //try to create message if valid
                                                    try {
                                                        Message m = new Message(mess, loggedIn, recipient);
                                                        userMessages.add(m);
                                                        System.out.println("Message added!");

                                                        //write message to files
                                                        SendMessages.customerSendsMessage(
                                                                m.getRecipient().getUsername(),
                                                                m.getSender().getUsername(),
                                                                m.getMessage()
                                                        );
                                                    } catch (Exception e) {
                                                        // if message failed, get reason why
                                                        System.out.println(e.getMessage());
                                                    }
                                                }

                                            }

                                        }
                                    }
                                    break;

                                case 3:
                                    //code to block users

                                    //ask for the email o the person the logged in user wishes to block
                                    System.out.println("Name the email of the user you wish to block");
                                    String blockEmail = scanner.nextLine();

                                    //see if the blocked user exits
                                    boolean found = false;
                                    for (User user : users) {
                                        if (user.getUsername().equals(blockEmail)) {
                                            loggedIn.block(user);
                                            found = true;
                                            break;
                                        }
                                    }

                                    //if found continue.
                                    if (found) {
                                        try {
                                            //save blocking information to file
                                            File f = null;
                                            //get path based on if the logged in user is a seller or not
                                            if (loggedIn.isSeller()) {
                                                f = new File("Database/Lists/SellerAccountsList.txt");
                                            } else {
                                                f = new File("Database/Lists/CustomerAccountsList.txt");
                                            }

                                            //get a reader initialized
                                            BufferedReader reader = new BufferedReader(new FileReader(f));

                                            //read a line and make an arraylist of lines
                                            String line = reader.readLine();
                                            ArrayList<String> lines = new ArrayList<>();

                                            //loop through file
                                            while (line != null) {
                                                String[] lineArr = line.split(",");

                                                //if you find logged in user's line, update their blocking info
                                                if (lineArr[0].equals(loggedIn.getUsername())
                                                        && lineArr[1].equals(loggedIn.getPassword())) {

                                                    //get their blockedList and make it into an array
                                                    ArrayList<User> blockedList = loggedIn.getBlocked();
                                                    String[] blocked = new String[blockedList.size()];
                                                    for (int i = 0; i < blockedList.size(); i++) {
                                                        blocked[i] = blockedList.get(i).getUsername();
                                                    }

                                                    //make the array into a string and update lineArr
                                                    String blockedStr = Arrays.toString(blocked);
                                                    lineArr[2] = blockedStr;

                                                }

                                                //update the lines and add them to the arraylist
                                                String backToString = Arrays.toString(lineArr);
                                                backToString = backToString.replaceAll(" ", "");
                                                lines.add(backToString.substring(1, backToString.length() - 1));
                                                line = reader.readLine();
                                            }

                                            //open a writer and add all lines to the file
                                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
                                            for (String l : lines) {
                                                writer.println(l);
                                            }

                                            writer.flush();
                                            writer.close();


                                        } catch (Exception e) {
                                            System.out.println("error reading files");
                                        }
                                        System.out.println("Blocked user!");
                                    } else {
                                        System.out.println("Please select a valid user to block");
                                    }
                                    break;
                                case 4:
                                    // Calls method to export the message
                                    FileInExp.exportMessage(loggedIn.getUsername());
                                    break;
                                case 5:
                                    // Import text case
                                    // Checks if sender is seller
                                    if (loggedIn.isSeller()) {
                                        System.out.println("Select a Recipient:");
                                        ArrayList<User> customers = new ArrayList<>();
                                        StringBuilder p = new StringBuilder();
                                        p.append("{");
                                        // prints list of eligible users
                                        for (User user : users) {
                                            if (!user.isSeller()) {
                                                customers.add(user);
                                                p.append(user.getUsername()).append(",");

                                            }
                                        }

                                        p.deleteCharAt(p.length() - 1);
                                        p.append("}");
                                        System.out.println(p);
                                        String rec = scanner.nextLine();
                                        boolean foundCustomer = false;
                                        // validates to make sure inputed user is a real user
                                        for (User user : customers) {
                                            if (user.getUsername().equals(rec)) {
                                                foundCustomer = true;
                                            }
                                        }
                                        // if the user is real runs import message method
                                        if (!foundCustomer) {
                                            System.out.println("Please input one of the users allowed");
                                        } else {
                                            FileInExp.importMessage(loggedIn.getUsername(), rec, loggedIn.isSeller());
                                        }
                                    } else {
                                        HashMap<String, String[]> map = Parse.businesses();
                                        String[][] stores = map.values().toArray(new String[0][]);
                                        System.out.println("Do you wish to message a store or a seller directly? (Enter 'store' or 'seller'");
                                        String messageType = scanner.nextLine();
                                        // has user select if they want to message a store or a seller
                                        if (messageType.equalsIgnoreCase("store")) {
                                            System.out.println("Select one of the following stores to message");
                                            StringBuilder p = new StringBuilder("{");
                                            // prints a list of stores
                                            for (String[] store : stores) {
                                                for (String s : store) {
                                                    p.append(s).append(", ");
                                                }
                                            }
                                            p.append("}");
                                            p.deleteCharAt(p.length() - 2);
                                            System.out.println(p);

                                            String store = scanner.nextLine();
                                            String[] arr = null;
                                            // finds list of stores
                                            for (String[] storeList : stores) {
                                                for (String s : storeList) {
                                                    if (s.equals(store)) {
                                                        arr = storeList;
                                                        break;
                                                    }
                                                }
                                            }
                                            // find associated seller to stores selected
                                            String associatedSeller = "";
                                            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                if (entry.getValue().equals(arr)) {
                                                    associatedSeller = entry.getKey();
                                                }
                                            }
                                            // validates the selected seller
                                            String recipient = null;
                                            for (User u : users) {
                                                if (u.getUsername().equals(associatedSeller)) {
                                                    recipient = u.getUsername();
                                                }
                                            }
                                            // if the selller gets validated run the import message method
                                            if (recipient != null) {
                                                FileInExp.importMessage(loggedIn.getUsername(), recipient, loggedIn.isSeller());
                                            } else {
                                                System.out.println("Please input one of the allowed stores");
                                            }

                                        } else if (messageType.equalsIgnoreCase("seller")) {
                                            System.out.println("Select a Recipient:");
                                            ArrayList<User> customers = new ArrayList<>();
                                            StringBuilder p = new StringBuilder();
                                            p.append("{");
                                            // gets a list of valid sellers to message
                                            for (User user : users) {
                                                if (user.isSeller()) {
                                                    customers.add(user);
                                                    p.append(user.getUsername()).append(",");

                                                }
                                            }

                                            p.deleteCharAt(p.length() - 1);
                                            p.append("}");
                                            System.out.println(p);
                                            String rec = scanner.nextLine();
                                            boolean foundCustomer = false;
                                            // validates the users selection from the list of sellers
                                            for (User user : customers) {
                                                if (user.getUsername().equals(rec)) {
                                                    foundCustomer = true;
                                                }
                                            }
                                            // if the seller is valid 
                                            if (!foundCustomer) {
                                                System.out.println("Please input one of the users allowed");
                                            } else {
                                                FileInExp.importMessage(loggedIn.getUsername(), rec, loggedIn.isSeller());
                                            }
                                        }
                                    }
                                    break;
                                case 6:
                                    // calls the edit method
                                    EditDelete.editMessage(loggedIn.getUsername(), loggedIn.isSeller());
                                    userMessages = Parse.getMessages(email, loggedIn.isSeller(), !loggedIn.isSeller());
                                    break;
                                case 7:
                                    // calls the delete method
                                    EditDelete.deleteMessage(loggedIn.getUsername(), loggedIn.isSeller());
                                    userMessages = Parse.getMessages(email, loggedIn.isSeller(), !loggedIn.isSeller());
                                    break;
                                case 9:
                                    exited = true;
                                    // saves number of messages when exiting
                                    try {
                                        Scanner checkLines = new Scanner(new File("Accounts/" + loggedIn.getUsername() + ".txt"));
                                        int totLines = 0;
                                        ArrayList<String> lines = new ArrayList<String>();
                                        // gets the total number of messages
                                        while (checkLines.hasNextLine()) {
                                            totLines++;
                                            checkLines.nextLine();
                                        }
                                        File f = new File("Database/Lists/UserSizes.txt");
                                        Scanner readLines = new Scanner(f);
                                        boolean inFile = false;
                                        // overrides previous number of messages in the file for the user
                                        while (readLines.hasNextLine()) {
                                            String l = readLines.nextLine();
                                            lines.add(l);
                                            String[] parsed = l.split(",");
                                            if (parsed[0].equals(loggedIn.getUsername())) {
                                                inFile = true;
                                                lines.set(lines.size()-1, loggedIn.getUsername() + "," + totLines);
                                            }
                                        }
                                        if (!inFile) {
                                            lines.add(loggedIn.getUsername() + "," + totLines);
                                        }
                                        // replaces old file with the new one with updated messages
                                        f.delete();
                                        File f2 = new File("Database/Lists/UserSizes.txt");
                                        PrintWriter writer = new PrintWriter(f2);
                                        for (String i: lines) {
                                            writer.write(i + "\n");
                                        }
                                        writer.close();

                                    } catch (Exception e) {
                                        System.out.println("Invalid File While Exiting");
                                    }
                                    break;
                                case 8:
                                    //code to make logged in user invisible to another user

                                    //get name of person that the logged in user wants to hide from
                                    System.out.println("Name the email of the user you wish to be invisible to");
                                    String invisibleEmail = scanner.nextLine();

                                    //see if you can find that person
                                    boolean foundHiding = false;
                                    User hidingFrom = null;
                                    for (User user : users) {
                                        if (user.getUsername().equals(invisibleEmail)) {
                                            loggedIn.makeInvisible(user);
                                            foundHiding = true;
                                            hidingFrom = user;
                                            break;
                                        }
                                    }

                                    //if person found, continue. Else, print error
                                    if (foundHiding) {
                                        System.out.println("You are now hidden!");


                                        try {
                                            //update user information to include new invisible list
                                            File f = null;

                                            //set path based on the seller attribute of the person logged in user wants
                                            // to hide from
                                            if (hidingFrom.isSeller()) {
                                                f = new File("Database/Lists/SellerAccountsList.txt");
                                            } else {
                                                f = new File("Database/Lists/CustomerAccountsList.txt");
                                            }

                                            //open reader
                                            BufferedReader reader = new BufferedReader(new FileReader(f));

                                            //get a line and make a list of lines
                                            String line = reader.readLine();
                                            ArrayList<String> lines = new ArrayList<>();

                                            //loop through lines
                                            while (line != null) {
                                                String[] lineArr = line.split(",");

                                                //if you find user's line, update it
                                                if (lineArr[0].equals(hidingFrom.getUsername())
                                                        && lineArr[1].equals(hidingFrom.getPassword())) {

                                                    //get list of invisible and make it into a string
                                                    ArrayList<User> invisibleList = hidingFrom.getInvisible();
                                                    String[] invisible = new String[invisibleList.size()];
                                                    for (int i = 0; i < invisibleList.size(); i++) {
                                                        invisible[i] = invisibleList.get(i).getUsername();
                                                    }
                                                    String invisibleStr = Arrays.toString(invisible);

                                                    //update lineArr
                                                    lineArr[3] = invisibleStr;

                                                }

                                                //convert lineArr to string and add each line to the arrayList
                                                String backToString = Arrays.toString(lineArr);
                                                backToString = backToString.replaceAll(" ", "");
                                                lines.add(backToString.substring(1, backToString.length() - 1));
                                                line = reader.readLine();
                                            }

                                            //overwrite the current file with the new lines
                                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f,false)));
                                            for (String l : lines) {
                                                writer.println(l);
                                            }

                                            writer.flush();
                                            writer.close();



                                        } catch (Exception e) {
                                            System.out.println("error reading files");
                                        }

                                    } else {
                                        System.out.println("Please enter a valid user to hide from");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid selection");
                                    // Code for default case
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number (1,-9) to select one of the given options");
                        }
                    }
                } else if (userChoice == 2) {
                    while (true) {
                        //prompts user to sign up as either a buyer or seller
                        System.out.println(signUpBuyerOrSeller);
                        String response = scanner.nextLine().toLowerCase().trim();

                        //if the user response doesn't equal a valid option, loop until a correct option is chosen
                        if (!response.equals("b") && !response.equals("s")) {
                            isWrong = true;
                            while (isWrong) {
                                System.out.println(errorMessageBuyerOrSeller);
                                System.out.println(signUpBuyerOrSeller);
                                response = scanner.nextLine().toLowerCase().trim();
                                if (response.equals("b") || response.equals("s")) {
                                    isWrong = false;
                                }
                            }
                        }
                        //after user inputs either buyer or seller, email and password is prompted
                        System.out.println(emailPrompt);
                        String email = scanner.nextLine();
                        System.out.println(passwordPrompt);
                        String password = scanner.nextLine();

                        //prompts to user to verify their password
                        System.out.println(passwordVerifyPrompt);
                        String verifiedPassword = scanner.nextLine();


                        //if the verified password doesn't match the original password, enter a loop until they match
                        if (!verifiedPassword.equals(password)) {
                            passwordMatchCheck = true;
                            while (passwordMatchCheck) {
                                System.out.println(errorPasswordMatch);
                                System.out.println(passwordVerifyPrompt);
                                verifiedPassword = scanner.nextLine();
                                if (verifiedPassword.equals(password)) {
                                    passwordMatchCheck = false;
                                }

                            }
                        }
                        if (response.equals("b")) {
                            //add account to buyer list
                            String addCustomerAccount = Accounts.addCustomerAccount(email, password);
                            //if the account already exists, just break out of sign up loop
                            if (addCustomerAccount.contains("Customer account already exists")) {
                                System.out.println(farewellMessage);
                                return;
                            } else {
                                //prompts a thank you, then exists
                                System.out.println(signUpThankYou);
                                System.out.println(farewellMessage);
                                return;
                            }
                        }
                        if (response.equals("s")) {
                            //add account to buyer list
                            String addSellerAccount = Accounts.addSellerAccount(email, password);
                            //if the account already exists, just break out of sign up loop
                            if (addSellerAccount.contains("Seller account already exists!")) {
                                System.out.println(farewellMessage);
                                return;
                            } else {
                                //prompts the store owner what stores are under their name, seperated by a comma
                                System.out.println(storesPrompt);
                                String stores = scanner.nextLine();
                                Stores.appendStores(stores, email);

                                //prompts a thank you, then goes back to main menu
                                System.out.println(signUpThankYou);
                                System.out.println(farewellMessage);
                                return;
                            }
                        }
                    }
                    // if the user inputs 3 in the first menu, exit program
                } else if (userChoice == 3) {
                    System.out.println(farewellMessage);
                    isRunning = false;
                } else {
                    // if the user input is greater than 3 or less than one, print error then loop back to menu
                    System.out.println(errorValidInput);
                }
                //if the user enters a string instead of a number, print error then loop back to menu
            } catch (InputMismatchException e) {
                System.out.println(errorValidInput);
                scanner.nextLine();
            }
        }
    }
}
