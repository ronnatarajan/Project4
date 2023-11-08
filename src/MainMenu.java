package src;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    public static String welcomeMessage = "Welcome to Market Place Messaging System";
    public static String emailPrompt = "Please input your email.";
    public static String passwordPrompt = "Please input your password.";
    public static String farewellMessage = "Goodbye!";
    public static String signUpBuyerOrSeller = "Would you like to sign up as a Buyer 'B'  or a Seller 'S'?";
    public static String errorMessageBuyerOrSeller= "Error, please enter 'B' (Buyer) or 'S' (Seller)";
    public static String errorValidInput = "Error, please enter a valid input (1-3)";
    public static boolean isRunning = true;
    public static boolean isWrong = false;
    private static String ongoingMenu = "1. Login\n" +
            "2. Sign Up\n" +
            "3. Exit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println(welcomeMessage);

        while (isRunning) {
            System.out.println(ongoingMenu);
            int userChoice = scanner.nextInt();
            scanner.nextLine();
            if (userChoice == 1) {
                System.out.println(emailPrompt);
                String email = scanner.nextLine();
                System.out.println(passwordPrompt);
                String password = scanner.nextLine();
                Accounts.checkAccount(email, password, "Database/Lists/CustomerAccountsList.txt");

            } else if (userChoice == 2) {
                while (isRunning) {
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
                    // add a checker for valid input
                    if (response.equals("b")) {
                        Accounts.addCustomerAccount(email, password);
                        isRunning = false;
                        // add goodbye message
                    }
                    if (response.equals("s")) {
                        System.out.println(emailPrompt);
                        String sellerEmail = scanner.nextLine();
                        System.out.println(passwordPrompt);
                        String sellerPassword = scanner.nextLine();
                        //add in validate password
                        Accounts.addSellerAccount(sellerEmail, sellerPassword);
                        isRunning = false;
                        // add goodbye message
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
