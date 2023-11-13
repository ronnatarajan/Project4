package src.TestCases;

import src.User;

import java.util.ArrayList;

public class UserTestCase {
    public static void main(String[] args) {
        // Test Data
        User buyer = new User("Harley123@gmail.com", "Awesome", false);
        User seller = new User("Apple@icloud.com", "Apple", true);

        User seller2 = new User("Google@gmail.com", "Google", true);
        User seller3 = new User("Activision@gmail.com", "Activision", true);

        User seller4 = new User("PurdueStore@gmail.com", "Purdue", true);

        ArrayList<User> invisibleAndBlocked = new ArrayList<>();
        invisibleAndBlocked.add(seller2);
        invisibleAndBlocked.add(seller3);

        // sizeofblocked and blocked Test Cases
        buyer.block(seller);
        if (buyer.sizeofblocked() == 1) {
            System.out.println("sizeofblocked and blocked Test Case Passed");
        } else {
            System.out.println("sizeofblocked and blocked Test Case Failed");
        }

        // setBlocked and getBlocked Test Cases
        buyer.setBlocked(invisibleAndBlocked);
        if (buyer.getBlocked() == invisibleAndBlocked) {
            System.out.println("getBlocked and setBlocked Test Case Passed");
        } else {
            System.out.println("getBlocked and setBlocked Test Case Failed");
        }

        // setInvisible and getInvisible Test Cases
        buyer.setInvisible(invisibleAndBlocked);
        if (buyer.getInvisible() == invisibleAndBlocked) {
            System.out.println("getInvisible and setInvisible Test Case Passed");
        } else {
            System.out.println("getInvisible and setInvisible Test Case Failed");
        }

        // canSee Test Case
        if (!buyer.canSee(seller2)) {
            System.out.println("canSee Test Case Passed");
        } else {
            System.out.println("canSee Test Case Failed");
        }

        // makeInvisible Test Case
        buyer.makeInvisible(seller4);
        if (!seller4.canSee(buyer)) {
            System.out.println("makeInvisible Test Case Passed");
        } else {
            System.out.println("makeInvisible Test Case Failed");
        }

        // correctPassword Test Case
        if (buyer.correctPassword("Awesome")) {
            System.out.println("correctPassword Test Case Passed");
        } else {
            System.out.println("correctPassword Test Case Failed");
        }

        // hasBlocked Test Case
        if (buyer.hasBlocked(seller)) {
            System.out.println("hasBlocked Test Case Passed");
        } else {
            System.out.println("hasBlocked Test Case Failed");
        }


//hello
    }
}
