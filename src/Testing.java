package src;

import java.util.HashMap;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        Accounts.addCustomerAccount("Harley@gmail.com", "Elly123");
//        Accounts.addSellerAccount("BobHardware@gmail.com", "HelloWorld");
//
//        Stores.createStore("BobHardware@gmail.com", "Bob's Hardware");

        HashMap<String, String[]> map = new HashMap<>();
        map.put("Bob", new String[]{"FedEx", "Apple"});
        map.put("Cat", new String[]{"Google", "Purdue"});


        String[][] stores = map.values().toArray(new String[0][]);
        System.out.println(stores[0][1]);




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
