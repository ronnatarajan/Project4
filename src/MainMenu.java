package src;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    public static String welcomeMessage = "Welcome to Market Place Messaging System";
    public static String signUpPrompt = "Would you like to sign up? (Y/N)";
    public static String logInPrompt = "Would you like to log in? (Y/N)";
    public static String logInBuyerOrSeller = "Are you logging in as a Buyer or a Seller? (B/S)";
    public static String errorMessageYesOrNo = "Please enter 'Y' (yes) or 'N' (no)";
    public static String errorMessageBuyerOrSeller = "Error, please input 'B' for Buyer or 'S' for Seller";
    public static String farewellMessage = "Goodbye!";
    public static boolean isRunning = true;
    public static boolean isWrong = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Message> messages = new ArrayList<>();

        System.out.println(welcomeMessage);

        while (isRunning) {
            System.out.println(signUpPrompt);
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (!userInput.equals("y") && !userInput.equals("n")) {
                isWrong = true;
                while (isWrong) {
                    System.out.println(errorMessageYesOrNo);
                    System.out.println(signUpPrompt);
                    userInput = scanner.nextLine().trim().toLowerCase();
                    if (userInput.equals("y") || userInput.equals("n")) {
                        isWrong = false;
                        break;
                    }
                }
            }

            if (userInput.equals("y")) {
                // Sign-up implementation goes here

            } else if (userInput.equals("n")) {
                System.out.println(logInPrompt);
                String logInInput = scanner.nextLine().trim().toLowerCase();

                if (!logInInput.equals("y") && !logInInput.equals("n")) {
                    isWrong = true;
                    while (isWrong) {
                        System.out.println(errorMessageYesOrNo);
                        System.out.println(logInPrompt);
                        logInInput = scanner.nextLine().trim().toLowerCase();
                        if (logInInput.equals("y") || logInInput.equals("n")) {
                            isWrong = false;
                            break;
                        }
                    }
                }

                if (logInInput.equals("y")) {
                    System.out.println(logInBuyerOrSeller);
                    String userTypeInput = scanner.nextLine().trim().toLowerCase();

                    if (!userTypeInput.equals("y") && !userTypeInput.equals("n")) {
                        isWrong = true;
                        while (isWrong) {
                            System.out.println(errorMessageBuyerOrSeller);
                            System.out.println(logInBuyerOrSeller);
                            userTypeInput = scanner.nextLine().trim().toLowerCase();
                            if (userTypeInput.equals("y") || userTypeInput.equals("n")) {
                                isWrong = false;
                                break;
                            }
                        }
                    }

                    if (userTypeInput.equals("b")) {
                        // Implement buyer-specific functionality here

                    } else if (userTypeInput.equals("s")) {
                        // Implement seller-specific functionality here

                    } else {
                        System.out.println("Invalid option. Please enter 'B' or 'S' for Buyer or Seller.");
                    }
                } else if (logInInput.equals("n")) {
                    System.out.println(farewellMessage);
                    isRunning = false;
                }
            }
        }
    }
}
