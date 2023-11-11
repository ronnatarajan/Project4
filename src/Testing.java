package src;

import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        Accounts.addCustomerAccount("Harley@gmail.com", "Elly123");
//        Accounts.addSellerAccount("BobHardware@gmail.com", "HelloWorld");
//
//        Stores.createStore("BobHardware@gmail.com", "Bob's Hardware");

        String message = "I need it for a home building project.";

        SendMessages.customerSendsMessage("BobHardware@gmail.com","Harley@gmail.com", message);

        //SendMessages.printCustomerMessages("Harley@gmail.com", "BobHardware@gmail.com");







//
//        Scanner scan = new Scanner(System.in);
////        String email = scan.nextLine();
////        String password = scan.nextLine();
//
////          Accounts.addCustomerAccount("Harley@gmail.com","Elly123");
//          Accounts.addSellerAccount("BobHardware@gmail.com", "HelloWorld");
//          Stores.createStore("BobHardware@gmail.com", "Bob's Hardware");
////        Accounts.addCustomerAccount("Zack220@yahoo.com","Bulls23");
//
////        Accounts.addCustomerAccount("Randy23@hotmail.com", "Raptors");
//
////        Accounts.addCustomerAccount("Randy@gmail.com", "Elly123");
////        String message = "I was wondering what I could help you with.";
//////
////        SendMessages.customerReceivesMessages("Harley@gmail.com", "BobHardware@gmail.com", message);

    }
}
