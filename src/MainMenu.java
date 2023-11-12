package src;

import java.awt.desktop.UserSessionEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class MainMenu {
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
        Scanner scanner = new Scanner(System.in);


        System.out.println(welcomeMessage);
        ArrayList<User> users = Parse.getUsers();
        while (isRunning) {
            try {
                System.out.println(ongoingMenu);
                int userChoice = scanner.nextInt();
                scanner.nextLine();
                if (userChoice == 1) {
                    boolean correctInput = false;
                    String email = "";
                    String password = "";
                    while (!correctInput) {
                        System.out.println(emailPrompt);
                        email = scanner.nextLine();
                        System.out.println(passwordPrompt);
                        password = scanner.nextLine();
                        String checkC = Accounts.checkAccount(email, password, "Database/Lists/CustomerAccountsList.txt");
                        String checkS = Accounts.checkAccount(email, password, "Database/Lists/SellerAccountsList.txt");
                        if (checkC.equals("Found account") || checkS.equals("Found account")) {
                            correctInput = true;
                        } else {
                            System.out.println("{" + errorInvalidCredentials + "}");
                        }
                    }


                    User loggedIn = null;
                    for (User user : users) {
                        if (user.getUsername().equals(email) && user.getPassword().equals(password)) {
                            loggedIn = user;
                        }
                    }
                    ArrayList<Message> userMessages = Parse.getMessages(email, loggedIn.isSeller(), !loggedIn.isSeller());

                    boolean exited = false;
                    while (!exited) {
                        System.out.println(mainMenu);

                        try {
                            int selection = Integer.parseInt(scanner.next());
                            scanner.nextLine();

                            if (selection < 1 || selection > 9) {
                                throw new NumberFormatException();
                            }
                            switch (selection) {
                                //view list of messages
                                case 1:
                                    for (Message message : userMessages) {
                                        System.out.println(message.toString());
                                    }
                                    break;
                                //send message
                                case 2:
                                    if (users.size() < 2) {
                                        System.out.println("There's no one to message!");
                                    } else {
                                        if (loggedIn.isSeller()) {
                                            //seller implementation
                                            System.out.println("Pick one of the customers below to message");
                                            ArrayList<User> customers = new ArrayList<>();

                                            StringBuilder p = new StringBuilder();
                                            p.append("{");
                                            for (User user : users) {
                                                if (!user.isSeller() && !loggedIn.cannotSee(user)) {
                                                    customers.add(user);
                                                    p.append(user.getUsername()).append(",");

                                                }
                                            }

                                            p.deleteCharAt(p.length() - 1);
                                            p.append("}");
                                            System.out.println(p);

                                            String customerSelection = scanner.nextLine();

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
                                                System.out.println("Please type in a message");
                                                String m = scanner.nextLine();

                                                try {
                                                    Message message = new Message(m, loggedIn, recipient);
                                                    userMessages.add(message);
                                                    System.out.println("Message added!");


                                                    SendMessages.sellerSendsMessage(
                                                            message.getSender().getUsername(),
                                                            message.getRecipient().getUsername(),
                                                            message.getMessage()
                                                    );


                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }

                                        } else {
                                            //customer implementation
                                            HashMap<String, String[]> map = Parse.businesses();
                                            String[][] stores = map.values().toArray(new String[0][]);
                                            System.out.println("Do you wish to message a store or a seller directly? (Enter 'store' or 'seller')");
                                            String messageType = scanner.nextLine();
                                            if (messageType.equalsIgnoreCase("store")) {
                                                System.out.println("Select one of the following stores to message");
                                                StringBuilder p = new StringBuilder("{");

                                                ArrayList<String> sellerEmails = new ArrayList<>();
                                                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                    sellerEmails.add(entry.getKey());
                                                }

                                                ArrayList<String> validSellers = new ArrayList<>();
                                                for (User u : users) {
                                                    for (String e : sellerEmails) {
                                                        if (u.getUsername().equals(e) && !loggedIn.cannotSee(u)) {
                                                            validSellers.add(e);
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

                                                String store = scanner.nextLine();
                                                String[] arr = null;

                                                for (String[] storeList : stores) {
                                                    for (String s : storeList) {
                                                        if (s.equals(store)) {
                                                            arr = storeList;
                                                            break;
                                                        }
                                                    }
                                                }

                                                String associatedSeller = "";
                                                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                    if (entry.getValue().equals(arr)) {
                                                        associatedSeller = entry.getKey();
                                                    }
                                                }

                                                User recipient = null;
                                                boolean found = false;
                                                for (User u : users) {
                                                    if (u.getUsername().equals(associatedSeller)) {
                                                        recipient = u;
                                                        found = true;
                                                    }
                                                }

                                                if (!found) {
                                                    System.out.println("Please enter a valid user to message");
                                                } else {
                                                    System.out.println("Please enter a message");
                                                    String mess = scanner.nextLine();

                                                    try {
                                                        Message m = new Message(mess, loggedIn, recipient);
                                                        userMessages.add(m);
                                                        System.out.println("Message added!");

                                                        SendMessages.customerSendsMessage(
                                                                m.getRecipient().getUsername(),
                                                                m.getSender().getUsername(),
                                                                m.getMessage()
                                                        );
                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }

                                            } else if (messageType.equalsIgnoreCase("seller")) {
                                                System.out.println("Select one of the following sellers to message");
                                                StringBuilder p = new StringBuilder("{");

                                                ArrayList<String> sellerEmails = new ArrayList<>();
                                                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                    sellerEmails.add(entry.getKey());
                                                }

                                                ArrayList<String> validSellers = new ArrayList<>();
                                                for (User u : users) {
                                                    for (String e : sellerEmails) {
                                                        if (u.getUsername().equals(e) && !loggedIn.cannotSee(u)) {
                                                            validSellers.add(e);
                                                        }
                                                    }
                                                }

                                                for (String seller : validSellers) {
                                                    p.append(seller).append(",");
                                                }
                                                p.append("}");
                                                p.deleteCharAt(p.length() - 2);
                                                System.out.println(p);

                                                String seller = scanner.nextLine();
                                                User recipient = null;
                                                boolean found = false;
                                                for (User u : users) {
                                                    if (u.getUsername().equals(seller)) {
                                                        recipient = u;
                                                        found = true;
                                                    }
                                                }

                                                if (!found) {
                                                    System.out.println("Please enter a valid user to message");
                                                } else {
                                                    System.out.println("Please enter a message");
                                                    String mess = scanner.nextLine();

                                                    try {
                                                        Message m = new Message(mess, loggedIn, recipient);
                                                        userMessages.add(m);
                                                        System.out.println("Message added!");

                                                        SendMessages.customerSendsMessage(
                                                                m.getRecipient().getUsername(),
                                                                m.getSender().getUsername(),
                                                                m.getMessage()
                                                        );
                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }

                                            }

                                        }
                                    }
                                    break;

                                case 3:
                                    System.out.println("Name the email of the user you wish to block");
                                    String blockEmail = scanner.nextLine();

                                    for (User user : users) {
                                        if (user.getUsername().equals(blockEmail)) {
                                            loggedIn.block(user);
                                            System.out.println(loggedIn.getUsername() + " has blocked " + loggedIn.sizeofblocked());
                                            break;
                                        }
                                    }
                                    System.out.println("Blocked user!");
                                    break;
                                case 4:
                                    FileInExp.exportMessage(loggedIn.getUsername());
                                    break;
                                case 5:
                                    if (loggedIn.isSeller()) {
                                        System.out.println("Select a Recipient:");
                                        ArrayList<User> customers = new ArrayList<>();
                                        StringBuilder p = new StringBuilder();
                                        p.append("{");
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
                                        boolean found = false;

                                        for (User user : customers) {
                                            if (user.getUsername().equals(rec)) {
                                                found = true;
                                            }
                                        }
                                        if (!found) {
                                            System.out.println("Please input one of the users allowed");
                                        } else {
                                            FileInExp.importMessage(loggedIn.getUsername(), rec, loggedIn.isSeller());
                                        }
                                    } else {
                                        HashMap<String, String[]> map = Parse.businesses();
                                        String[][] stores = map.values().toArray(new String[0][]);
                                        System.out.println("Do you wish to message a store or a seller directly? (Enter 'store' or 'seller'");
                                        String messageType = scanner.nextLine();
                                        if (messageType.equals("store")) {
                                            System.out.println("Select one of the following stores to message");
                                            StringBuilder p = new StringBuilder("{");
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

                                            for (String[] storeList : stores) {
                                                for (String s : storeList) {
                                                    if (s.equals(store)) {
                                                        arr = storeList;
                                                        break;
                                                    }
                                                }
                                            }

                                            String associatedSeller = "";
                                            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                                if (entry.getValue().equals(arr)) {
                                                    associatedSeller = entry.getKey();
                                                }
                                            }

                                            String recipient = null;
                                            for (User u : users) {
                                                if (u.getUsername().equals(associatedSeller)) {
                                                    recipient = u.getUsername();
                                                }
                                            }

                                            if (recipient != null) {
                                                FileInExp.importMessage(loggedIn.getUsername(), recipient, loggedIn.isSeller());
                                            } else {
                                                System.out.println("Please input one of the allowed stores");
                                            }

                                        } else {
                                            System.out.println("Select a Recipient:");
                                            ArrayList<User> customers = new ArrayList<>();
                                            StringBuilder p = new StringBuilder();
                                            p.append("{");
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
                                            boolean found = false;

                                            for (User user : customers) {
                                                if (user.getUsername().equals(rec)) {
                                                    found = true;
                                                }
                                            }
                                            if (!found) {
                                                System.out.println("Please input one of the users allowed");
                                            } else {
                                                FileInExp.importMessage(loggedIn.getUsername(), rec, loggedIn.isSeller());
                                            }
                                        }
                                    }
                                    break;
                                case 6:
                                    EditDelete.editMessage(loggedIn.getUsername(), loggedIn.isSeller());
                                    break;
                                case 7:
                                    EditDelete.deleteMessage(loggedIn.getUsername(), loggedIn.isSeller());
                                    break;
                                case 9:
                                    exited = true;

                                    break;
                                case 8:
                                    System.out.println("Name the email of the user you wish to be invisible to");
                                    String invisibleEmail = scanner.nextLine();

                                    boolean found = false;
                                    for (User user : users) {
                                        if (user.getUsername().equals(invisibleEmail)) {
                                            loggedIn.makeInvisible(user);
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (found) {
                                        System.out.println("You are now hidden!");
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
                            System.out.println("Please input a number (1,2, or 3) to select one of the given options");
                        }
                    }
                } else if (userChoice == 2) {
                    boolean run = true;
                    while (run) {
                        System.out.println(signUpBuyerOrSeller);
                        String response = scanner.nextLine().toLowerCase().trim();

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
                        System.out.println(emailPrompt);
                        String email = scanner.nextLine();
                        System.out.println(passwordPrompt);
                        String password = scanner.nextLine();

                        System.out.println(passwordVerifyPrompt);
                        String verifiedPassword = scanner.nextLine();

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
                            String addCustomerAccount = Accounts.addCustomerAccount(email, password);
                            if (addCustomerAccount.contains("Customer account already exists")) {
                                break;
                            } else {
                                System.out.println(signUpThankYou);
                                MainMenu.main(null);
                            }
                        }
                        if (response.equals("s")) {
                            String addSellerAccount = Accounts.addSellerAccount(email, password);
                            if (addSellerAccount.contains("Seller account already exists!")) {
                                break;
                            } else {
                                System.out.println(storesPrompt);
                                String stores = scanner.nextLine();
                                Stores.appendStores(stores, email);
                                System.out.println(signUpThankYou);
                                MainMenu.main(null);
                            }
                        }
                    }
                } else if (userChoice == 3) {
                    System.out.println(farewellMessage);
                    isRunning = false;
                } else {
                    System.out.println(errorValidInput);
                }
            } catch (InputMismatchException e) {
                System.out.println(errorValidInput);
                scanner.nextLine();
            }
        }
    }
}
