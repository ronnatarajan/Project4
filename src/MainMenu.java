package src;

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
    public static boolean isWrong = false;
    public static boolean passwordMatchCheck = false;
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
                        Accounts.addCustomerAccount(email, password);
                        System.out.println(signUpThankYou);
                        MainMenu.main(null);
                    }
                    if (response.equals("s")) {
                        Accounts.addSellerAccount(email, password);
                        if (Accounts.checkAccount(email, password, "Database/Lists/SellerAccountsList.txt").equals("Email or password is incorrect")) {
                            System.out.println("Seller account already exists!");
                            return;
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
