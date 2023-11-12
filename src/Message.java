package src;

public class Message {
    //saves message
    String message;
    //sender
    User sender;
    //recipient
    User recipient;

    /**
     * initialize message
     * @param message String
     * @param sender User
     * @param recipient User
     */
    public Message(String message, User sender, User recipient) throws InvalidMessageException {
        if (sender.isSeller() == recipient.isSeller()) {
            if (sender.isSeller()) {
                throw new InvalidMessageException("Seller cannot message another seller");
            } else {
                throw new InvalidMessageException("Buyer cannot message another buyer");
            }
        }

        if (recipient.hasBlocked(sender)) {
            System.out.println("threw exception");
            throw new InvalidMessageException("You have been blocked");
        }
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * getter for message
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter for sender
     * @return String
     */
    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return "Sender: " + sender.getUsername() + "; Message: " + this.message + "; Recipient: " + recipient.getUsername();
    }
}
