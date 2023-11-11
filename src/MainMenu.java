package src;

import java.awt.desktop.UserSessionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
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
            "3. Delete Message";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println(welcomeMessage);

        while (isRunning) {
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

                ArrayList<User> users = Parse.getUsers();
                User loggedIn = null;
                for (User user : users) {
                    if (user.getUsername().equals(email) && user.getPassword().equals(password)) {
                        loggedIn = user;
                    }
                }

                ArrayList<Message> userMessages = Parse.getMessages(email, loggedIn.isSeller());

                boolean exited = false;
                while (!exited) {
                    System.out.println(mainMenu);

                    try {
                        int selection = Integer.parseInt(scanner.next());
                        scanner.nextLine();

                        if (selection != 1 || selection != 2 || selection != 3) {
                            throw new NumberFormatException();
                        }

                        switch(selection) {
                            //view list of messages
                            case 1:
                                for (Message message : userMessages) {
                                    System.out.println(message.toString());
                                }

                            //send message
                            case 2:
                                if (loggedIn.isSeller()) {
                                    //seller implementation
                                    ArrayList<User> customers = new ArrayList<>();

                                    StringBuilder p = new StringBuilder();
                                    p.append("{");
                                    for (User user : users) {
                                        if (!user.isSeller()) {
                                            customers.add(user);
                                            p.append(user.getUsername()).append(",");

                                        }
                                    }
                                    p.deleteCharAt(p.length()-2);

                                } else {
                                    //customer implementation
                                }

                        }
                    } catch (NumberFormatException e){
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
        }
    }
}
