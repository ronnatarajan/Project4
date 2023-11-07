import java.io.*;

public class SendMessages {

    public static void customerReceivesMessages(String customerEmail, String sellerEmail, String newMessage) {
        String fileName = "Accounts/CustomerAccounts/" + customerEmail + "/" +
                sellerEmail + ".txt";

        File messageFile = new File(fileName);

        if (messageFile.exists()) {
            appendMessage(fileName, newMessage);
        } else {
            try {
                messageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // This is to append the message to the seller's message history file as well
        String sellerFileName = "Accounts/SellerAccounts/" + sellerEmail + "/" +
                customerEmail + ".txt";

        File sellerMessageFile = new File(sellerFileName);

        if (sellerMessageFile.exists()) {
            appendMessage(sellerFileName, newMessage);
        } else {
            try {
                sellerMessageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sellerReceivesMessages(String sellerEmail, String customerEmail, String newMessage) {
        String fileName = "Accounts/CustomerAccounts/" + sellerEmail + "/" +
                customerEmail + ".txt";

        File messageFile = new File(fileName);

        if (messageFile.exists()) {
            appendMessage(fileName, newMessage);
        } else {
            try {
                messageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // This is to append the message to the customer's message history file as well
        String customerFileName = "Accounts/SellerAccounts/" + customerEmail + "/" +
                sellerEmail + ".txt";

        File customerMessageFile = new File(customerFileName);

        if (customerMessageFile.exists()) {
            appendMessage(customerFileName, newMessage);
        } else {
            try {
                customerMessageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public static void appendMessage(String fileName, String newMessage) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName,true)));
            printWriter.print(newMessage + "\n");

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
