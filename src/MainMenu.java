package src;

import java.util.ArrayList;
import java.util.Scanner;


public class MainMenu {
    public static String welcomeMessage = "Welcome to Market Place Messaging System";
    public static String signUpOrLogIn = "Would you like to sign up? (Y/N)";
    public static String logInPrompt = "Would you like to log in? (Y/N)";
    public static String logInBuyerOrSeller = "Are you logging in as a Buyer or a Seller? (B/Y)";
    public static String errorMessageYesOrNo = "Please enter an input of either Y (yes) or N (no)";
    public static String farewellMessage = "Goodbye!";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(welcomeMessage);
        System.out.println(signUpOrLogIn);

        // Prompting user to sign up or log in, then implementing features based on what was prompted.
        String userInput = scanner.nextLine();

        // If user input is not a valid option, print error message and reprompt
        while (!userInput.equalsIgnoreCase("y") || !userInput.equalsIgnoreCase("n")) {
            System.out.println(errorMessageYesOrNo);
            System.out.println(signUpOrLogIn);
            userInput = scanner.nextLine();
        }
        if (userInput.equalsIgnoreCase("y")) {
            while (userInput.equalsIgnoreCase("y")) {
                // Sign up implementation goes here
            }
        } else if(userInput.equalsIgnoreCase("n")) {

            System.out.println(logInPrompt);
            String logInInput = scanner.nextLine();

            while (!logInInput.equalsIgnoreCase("y") || !logInInput.equalsIgnoreCase("n")) {
                System.out.println(errorMessageYesOrNo);
                System.out.println(logInPrompt);
                logInInput = scanner.nextLine();
            }

            if (logInInput.equalsIgnoreCase("y")) {
                while (logInInput.equalsIgnoreCase("y")) {
                    System.out.println(logInBuyerOrSeller);
                    // Buyer and seller implementation goes here.

                }

            } else if (logInInput.equalsIgnoreCase("n")) {
                System.out.println(farewellMessage);
                return;
            }
        }

    // Store user list and messages list
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Message> messages = new ArrayList<>();

}
}
